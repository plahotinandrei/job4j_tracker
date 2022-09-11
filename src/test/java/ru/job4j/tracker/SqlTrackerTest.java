package ru.job4j.tracker;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeClass
    public static void initConnection() {
        try (
                InputStream in
                        = SqlTrackerTest.class.getClassLoader()
                        .getResourceAsStream("test.properties")
        ) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        Store tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenReplace() {
        Store tracker = new SqlTracker(connection);
        Item bug = new Item();
        bug.setName("Bug");
        tracker.add(bug);
        int id = bug.getId();
        Item bugWithDesc = new Item();
        bugWithDesc.setName("Bug with description");
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenDelete() {
        Store tracker = new SqlTracker(connection);
        Item bug = new Item();
        bug.setName("Bug");
        tracker.add(bug);
        int id = bug.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id), is(nullValue()));
    }

    @Test
    public void whenFindAll() {
        Store tracker = new SqlTracker(connection);
        Item item1 = new Item("item1");
        tracker.add(item1);
        Item item2 = new Item("item2");
        tracker.add(item2);
        Item item3 = new Item("item3");
        tracker.add(item3);
        assertThat(tracker.findAll(), is(List.of(item1, item2, item3)));
    }

    @Test
    public void whenFindAllThenEmptyList() {
        Store tracker = new SqlTracker(connection);
        assertThat(tracker.findAll(), is(List.of()));
    }

    @Test
    public void whenFindByName() {
        Store tracker = new SqlTracker(connection);
        Item item1 = new Item("item1");
        tracker.add(item1);
        Item item2 = new Item("item1");
        tracker.add(item2);
        Item item3 = new Item("item3");
        tracker.add(item3);
        assertThat(tracker.findByName("item1"), is(List.of(item1, item2)));
    }

    @Test
    public void whenFindByNameThenEmptyList() {
        Store tracker = new SqlTracker(connection);
        assertThat(tracker.findByName("item1"), is(List.of()));
    }

    @Test
    public void whenFindById() {
        Store tracker = new SqlTracker(connection);
        Item item1 = new Item("item1");
        tracker.add(item1);
        Item item2 = new Item("item2");
        tracker.add(item2);
        assertThat(tracker.findById(item1.getId()), is(item1));
    }

    @Test
    public void whenFindByIdThenNull() {
        Store tracker = new SqlTracker(connection);
        Item item = new Item("item1");
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }
}

