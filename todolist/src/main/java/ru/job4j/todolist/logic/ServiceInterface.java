package ru.job4j.todolist.logic;

import ru.job4j.todolist.models.Task;

import java.util.List;

/**
 * the interface for the service layer.
 */
public interface ServiceInterface {

    List<Task> getAll();

    void changeStatus(Task task);

    Task addTask(Task task);
}

