package ru.job4j.todolist.logic;

import ru.job4j.todolist.connectors.Connector;
import ru.job4j.todolist.connectors.DataBaseConnector;
import ru.job4j.todolist.models.Task;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * the class for the validating and servicing.
 */
public class Service implements ServiceInterface {

    /**
     * the class instance.
     */
    private static final Service INSTANCE = new Service();

    /**
     * the instance of the connector.
     */
    private final Connector connector = DataBaseConnector.getInstance();

    private Service() {

    }

    /**
     * the instance getter.
     * @return instance.
     */
    public static Service getInstance() {
        return INSTANCE;
    }

    /**
     * returns the list of the tasks.
     * @return the list.
     */
    public List<Task> getAll() {
        return this.connector.getAll();
    }

    /**
     * changes the status of the task.
     * @param task - the task with changed status.
     */
    public void changeStatus(Task task) {
        this.connector.changeStatus(task);
    }

    /**
     * adds the new task to the data base.
     * adds date and sets status as false (not done).
     * @param task - the new task
     * @return - the task with its id in the database.
     */
    public Task addTask(Task task) {
        task.setDone(false);
        Calendar date = GregorianCalendar.getInstance();
        date.setTimeInMillis(System.currentTimeMillis());
        task.setCreated(date);
        return this.connector.addTask(task);
    }
}
