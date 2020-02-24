package ru.job4j.todolist.connectors;

import ru.job4j.todolist.models.Task;

import java.util.List;

/**
 * the interface for data base connectors.
 */
public interface Connector {

    List<Task> getAll();

    void changeStatus(Task task);

    Task addTask(Task task);
}
