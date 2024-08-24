package ru.job4j.tracker;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HbmTracker implements Store, AutoCloseable {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    private final CrudRepository crudRepository = new CrudRepository(sf);

    @Override
    public Item add(Item item) {
        int id = crudRepository.tx((session) -> (int) session.save(item));
        item.setId(id);
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        return crudRepository.run(
                "UPDATE Item SET name = :name, created = :created WHERE id = :id",
                Map.of("id", id, "name", item.getName(), "created", item.getCreated())
        );
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.run("DELETE Item WHERE id = :id", Map.of("id", id));
    }

    @Override
    public List<Item> findAll() {
        return crudRepository.query("FROM Item", Item.class);
    }

    @Override
    public List<Item> findByName(String key) {
        return crudRepository.query(
                "FROM Item AS i WHERE i.name = :name",
                Item.class, Map.of("name", key)
        );
    }

    @Override
    public Item findById(int id) {
        Optional<Item> itemOptional = crudRepository.optional(
                "FROM Item AS i WHERE i.id = :id",
                Item.class, Map.of("id", id)
        );
        return itemOptional.orElse(null);
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
