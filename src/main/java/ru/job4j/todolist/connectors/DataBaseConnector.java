package ru.job4j.todolist.connectors;

import ru.job4j.todolist.models.Task;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * the class for connecting to the data base.
 */
public class DataBaseConnector implements Connector {

    /**
     * the instance of the class.
     */
    private static final DataBaseConnector INSTANCE = new DataBaseConnector();

    /**
     * the main constructor.
     */
    private DataBaseConnector() {

    }

    /**
     * the entity manager.
     */
    private EntityManager em = Persistence
            .createEntityManagerFactory("Tasks")
            .createEntityManager();

    /**
     * the instance getter
     * @return instance
     */
    public static DataBaseConnector getInstance() {
        return INSTANCE;
    }

    /**
     * the method for getting all rows from the data base.
     * @return the list of the tasks
     */
    public List<Task> getAll() {
        TypedQuery<Task> query = em.createNamedQuery("Task.getAll", Task.class);
        return query.getResultList();
    }

    /**
     * the method for changing status of the task.
     * @param task the task with required changes.
     */
    public void changeStatus(Task task) {
        em.getTransaction().begin();
        Task existingTask = em.find(Task.class, task.getId());
        existingTask.setDone(task.isDone());
        em.merge(existingTask);
        em.getTransaction().commit();
    }

    /**
     * the method for adding the new task to the data base.
     * @param task - the new task
     * @return - the task with its id in the data base.
     */
    public Task addTask(Task task) {
        em.getTransaction().begin();
        Task newTask = em.merge(task);
        em.getTransaction().commit();
        return newTask;
    }

}
