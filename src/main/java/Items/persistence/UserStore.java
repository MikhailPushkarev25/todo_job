package Items.persistence;

import Items.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class UserStore {

    private final SessionFactory sf;

    public UserStore(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Optional<User> create(User user) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return Optional.of(user);
    }

    public List<User> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from User ").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public Optional<User> findEmailAndPwd(String email, String password) {
        return this.tx(session -> session.createQuery("from User where email = :newEmail and password = :newPassword")
                .setParameter("newEmail", email)
                .setParameter("newPassword", password)
                .setMaxResults(1).uniqueResultOptional()
        );
    }
}
