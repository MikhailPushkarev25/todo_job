package Items.persistence;

import Items.model.Category;
import Items.model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemStore {

    private final SessionFactory sf;

    public ItemStore(SessionFactory sf) {
        this.sf = sf;
    }

    public void add(Item item, List<String> catId) {
        Session session = sf.openSession();
        session.beginTransaction();
        for (String id : catId) {
            Category category = (Category) session.createQuery("from Category c where id = :cid order by :cid")
                    .setParameter("cid", Integer.parseInt(id))
                            .setMaxResults(1).uniqueResult();
            item.getCategories().add(category);
        }
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }

    public List all() {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from Item ").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public void update(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.createQuery("update Item i set i.name = :newName, i.description = :newDescription where i.id = :Fid")
                .setParameter("newName", item.getName())
                .setParameter("newDescription", item.getDescription())
                .setParameter("Fid", item.getId())
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public Item findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item item = session.get(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    public void delete(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }

}
