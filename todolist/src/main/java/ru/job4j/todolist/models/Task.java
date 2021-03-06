package ru.job4j.todolist.models;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

/**
 * the class of the task.
 */
@Entity
@Table(name = "tasks")
@NamedQuery(name = "Task.getAll", query = "from Task")
public class Task {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String desc;

    @Column(name = "created")
    private Calendar created;

    @Column(name = "done")
    private boolean done;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(name, task.name)
                && Objects.equals(desc, task.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, desc);
    }
}
