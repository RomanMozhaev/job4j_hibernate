package ru.job4j.firsthiber;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.firsthiber.models.User;


import java.util.GregorianCalendar;
import java.util.List;
import java.util.function.Function;

public class HibernateRun {

    public static void main(String[] args) {
        HibernateRun run = new HibernateRun();
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        User user = run.save(factory, "Roman");
        List list = run.getEntry(factory, user);
        run.print(list);
        user = run.update(factory, (User) list.get(0), "Alex");
        list = run.getEntry(factory, user);
        run.print(list);
        run.delete(factory, (User) list.get(0));
        list = run.getAllEntries(factory);
        run.print(list);
        factory.close();
    }

    /**
     * creates and saves the new user to the data base.
     *
     * @param factory  session factory
     * @param userName User's name
     * @return the user instance.
     */
    private User save(SessionFactory factory, String userName) {
        return this.transaction(factory, session -> {
            User user = new User();
            user.setName(userName);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(System.currentTimeMillis());
            user.setExpired(calendar);
            session.save(user);
            return user;
        });
    }

    /**
     * @param factory session factory
     * @param user    - the user for updating
     * @param newName new User's name
     * @return the user instance.
     */
    private User update(SessionFactory factory, User user, String newName) {
        return this.transaction(factory, session -> {
            user.setName(newName);
            session.update(user);
            return user;
        });
    }

    /**
     * deletes the user from the data base.
     * @param factory - session factory.
     * @param user - the user for deleting.
     */
    private void delete(SessionFactory factory, User user) {
        this.transaction(factory, session -> {
            session.delete(user);
            return user;
        });
    }

    /**
     * finds the user in the data base.
     * @param factory - session factory.
     * @param user - the user for finding by id.
     * @return List of the users with requested id.
     */
    private List getEntry(SessionFactory factory, User user) {
        return this.transaction(factory, session -> {
            String hql = "from User where id = :user_id";
            Query query = session.createQuery(hql);
            query.setParameter("user_id", user.getId());
            List list = query.list();
            return list;
        });
    }

    /**
     * finds all users in the data base.
     * @param factory - session factory.
     * @return List of the users.
     */
    private List getAllEntries(SessionFactory factory) {
        return this.transaction(factory, session -> {
            String hql = "from User";
            Query query = session.createQuery(hql);
            List list = query.list();
            return list;
        });
    }


    /**
     * the wrapper for methods.
     * @param factory - session factory.
     * @param operation - unique operation for each method.
     * @param <T> - returning type.
     * @return - result of the unique operation.
     */
    private <T> T transaction(SessionFactory factory, Function<Session, T> operation) {
        Session session = factory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            T result = operation.apply(session);
            tx.commit();
            return result;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * prints in the console the list.
     * @param list - the list for printing.
     */
    private void print(List list) {
        for (Object u : list) {
            System.out.println(u.toString());
        }
    }
}
