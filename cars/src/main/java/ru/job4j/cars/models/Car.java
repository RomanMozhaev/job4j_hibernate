package ru.job4j.cars.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Car {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    private String model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "id"))
    private Engine engine;

    @ManyToMany(mappedBy = "cars")
    private Set<Driver> drivers;

    public Car() {
    }

    public Car(String model, Engine engine) {
        this.model = model;
        this.engine = engine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }


    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }
}
