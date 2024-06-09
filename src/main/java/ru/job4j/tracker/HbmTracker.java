package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        sf.getCurrentSession();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            int id = (int) session.save(item);
            item.setId(id);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            rsl = session.createQuery(
                            "UPDATE Item SET name = :name, created = :created WHERE id = :id")
                    .setParameter("id", id)
                    .setParameter("name", item.getName())
                    .setParameter("created", item.getCreated())
                    .executeUpdate() > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            rsl = session.createQuery(
                            "DELETE Item WHERE id = :id")
                    .setParameter("id", id)
                    .executeUpdate() > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = List.of();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Query<Item> query = session.createQuery(
                    "from Item", Item.class);
            items = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = List.of();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Query<Item> query = session.createQuery(
                    "FROM Item AS i WHERE i.name = :name", Item.class);
            query.setParameter("name", key);
            items = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Query<Item> query = session.createQuery(
                    "FROM Item AS i WHERE i.id = :id", Item.class);
            query.setParameter("id", id);
            item = query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return item;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
