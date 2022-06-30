package Items.persistence;

import Items.model.Category;
import Items.model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class CategoryStore {

    private final SessionFactory sf;

    public CategoryStore(SessionFactory sf) {
        this.sf = sf;
    }

    public List<Category> findAllCategory() {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from Category ").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public Category findByIdCategory(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Category category = (Category) session.createQuery("from Category c where c.id = :fId ORDER BY :fId")
                .setParameter("fId", id)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return category;
    }
}
