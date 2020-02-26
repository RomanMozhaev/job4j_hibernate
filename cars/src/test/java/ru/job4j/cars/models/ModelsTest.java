package ru.job4j.cars.models;

import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ModelsTest {

    private Engine engine1;
    private Engine engine2;
    private Car car1;
    private Car car2;
    private Car car3;
    private Car car4;
    private Driver driver1;
    private Driver driver2;
    private Driver driver3;
    private final EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("Cars");


    @Before
    public void prepare() {
        this.engine1 = new Engine("16V");
        this.engine2 = new Engine("8V");
        this.car1 = new Car("HatchBack", engine2);
        this.car2 = new Car("Sedan", engine1);
        this.car3 = new Car("Minivan", engine1);
        this.car4 = new Car("Hatch Back sport", engine1);
        this.driver1 = new Driver("Alex", Set.of(this.car1, this.car2));
        this.driver2 = new Driver("Max", Set.of(this.car3, this.car4));
        this.driver3 = new Driver("Tom", Set.of(this.car1, this.car4));
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(this.engine1);
        em.persist(this.engine2);
        em.persist(this.car1);
        em.persist(this.car2);
        em.persist(this.car3);
        em.persist(this.car4);
        em.persist(this.driver1);
        em.persist(this.driver2);
        em.persist(this.driver3);
        tx.commit();
        em.close();

    }

    @Test
    public void create() {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query q = em.createQuery("select d From Driver d where d.name like :driverName");
        q.setParameter("driverName", "Alex");
        Driver driver = (Driver) q.getSingleResult();
        tx.commit();
        assertThat(driver.getCars(), is(Set.of(this.car1, this.car2)));
        em.close();
    }

    @Test
    public void edit() {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query q = em.createQuery("select d From Driver d where d.name like :driverName");
        q.setParameter("driverName", "Alex");
        Driver driver = (Driver) q.getSingleResult();
        Set<Car> cars = new HashSet<>(driver.getCars());
        cars.remove(this.car1);
        driver.setCars(cars);
        em.merge(driver);
        tx.commit();
        assertThat(driver.getCars(), is(Set.of(this.car2)));
        em.close();
    }

    @Test
    public void delete() {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query q1 = em.createQuery("delete from Driver d where d.name like :driverName");
        q1.setParameter("driverName", "Max");
        q1.executeUpdate();
        Query q2 = em.createQuery("select c From Car c where c.model like :modelName");
        q2.setParameter("modelName", "Minivan");
        Car car = (Car) q2.getSingleResult();
        Set<Driver> drivers = car.getDrivers();
        tx.commit();
        assertTrue(drivers.isEmpty());
        em.close();


    }
}