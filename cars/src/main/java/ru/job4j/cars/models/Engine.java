package ru.job4j.cars.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "engine")
public class Engine {

    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true, nullable = false)
    private String model;

    public Engine() {
    }

    public Engine(String model) {
        this.model = model;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Engine engine = (Engine) o;
        return Objects.equals(model, engine.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }
}
