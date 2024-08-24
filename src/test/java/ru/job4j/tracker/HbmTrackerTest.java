package ru.job4j.tracker;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import java.util.Map;
import static org.assertj.core.api.Assertions.*;

public class HbmTrackerTest {

    private static StandardServiceRegistry registry;

    private static CrudRepository crudRepository;

    @BeforeAll
    public static void init() {
        registry = new StandardServiceRegistryBuilder()
                .configure().build();
        final SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        crudRepository = new CrudRepository(sf);
    }

    @AfterAll
    public static void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @AfterEach
    public void wipeTable() {
        crudRepository.run("DELETE Item", Map.of());
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        Store tracker = new HbmTracker();
        Item item = tracker.add(new Item("item"));
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    public void whenReplace() {
        Store tracker = new HbmTracker();
        Item bug = tracker.add(new Item("Bug"));
        int id = bug.getId();
        tracker.replace(id, new Item("Bug with description"));
        assertThat(tracker.findById(id).getName()).isEqualTo("Bug with description");
    }

    @Test
    public void whenDelete() {
        Store tracker = new HbmTracker();
        Item bug = tracker.add(new Item("Bug"));
        int id = bug.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id)).isNull();
    }

    @Test
    public void whenFindAll() {
        Store tracker = new HbmTracker();
        Item item1 = tracker.add(new Item("item1"));
        Item item2 = tracker.add(new Item("item2"));
        Item item3 = tracker.add(new Item("item3"));
        assertThat(tracker.findAll()).contains(item1, item2, item3);
    }

    @Test
    public void whenFindAllThenEmptyList() {
        Store tracker = new HbmTracker();
        assertThat(tracker.findAll()).isEmpty();
    }

    @Test
    public void whenFindByName() {
        Store tracker = new HbmTracker();
        Item item1 = tracker.add(new Item("item1"));
        Item item2 = tracker.add(new Item("item1"));
        tracker.add(new Item("item3"));
        assertThat(tracker.findByName("item1")).contains(item1, item2);
    }

    @Test
    public void whenFindByNameThenEmptyList() {
        Store tracker = new HbmTracker();
        assertThat(tracker.findByName("item1")).isEmpty();
    }

    @Test
    public void whenFindById() {
        Store tracker = new HbmTracker();
        Item item1 = tracker.add(new Item("item1"));
        tracker.add(new Item("item2"));
        assertThat(tracker.findById(item1.getId())).isEqualTo(item1);
    }

    @Test
    public void whenFindByIdThenNull() {
        Store tracker = new HbmTracker();
        Item item = new Item("item1");
        assertThat(tracker.findById(item.getId())).isNull();
    }
}