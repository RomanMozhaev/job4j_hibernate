package ru.job4j;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.models.User;


import java.util.GregorianCalendar;
import java.util.List;

public class HibernateRun {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        //create
        session.beginTransaction();
        User user = new User();
        user.setName("Roman");
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(System.currentTimeMillis());
        user.setExpired(calendar);
        session.save(user);
        session.getTransaction().commit();
        System.out.println("Created user: ");
        String hql = "from User where id = :user_id";
        Query query = session.createQuery(hql);
        query.setParameter("user_id", user.getId());
        List list = query.list();
        for (Object entry : list) {
            System.out.println(entry);
        }
        System.out.println("User name updated: ");
        session.beginTransaction();
        user.setName("Alex");
        session.update(user);
        session.getTransaction().commit();
        list = query.list();
        for (Object entry : list) {
            System.out.println(entry);
        }
        System.out.println("User was deleted. All other users: ");
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        hql = "from User";
        query = session.createQuery(hql);
        list = query.list();
        for (Object entry : list) {
            System.out.println(entry);
        }
        session.close();
        factory.close();
    }
}
