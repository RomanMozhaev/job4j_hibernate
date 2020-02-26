package ru.job4j.cars.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Driver {

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String name;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Car> cars;


    public Driver() {
    }

    public Driver(String name, Set<Car> cars) {
        this.name = name;
        this.cars = cars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Driver driver = (Driver) o;
        return Objects.equals(name, driver.name)
                && Objects.equals(cars, driver.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cars);
    }
}
