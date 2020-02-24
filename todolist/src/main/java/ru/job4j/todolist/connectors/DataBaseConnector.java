package ru.job4j.todolist.connectors;

import ru.job4j.todolist.models.Task;

import javax.persistence.*;
import java.util.List;
import java.util.function.Function;

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
    private final EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("Tasks");

    /**
     * the instance getter
     *
     * @return instance
     */
    public static DataBaseConnector getInstance() {
        return INSTANCE;
    }

    /**
     * the method for getting all rows from the data base.
     *
     * @return the list of the tasks
     */
    public List<Task> getAll() {
        return this.tx(em -> {
            TypedQuery<Task> query = em.createNamedQuery("Task.getAll", Task.class);
            return query.getResultList();
        });
    }

    /**
     * the method for changing status of the task.
     *
     * @param task the task with required changes.
     */
    public void changeStatus(Task task) {
        this.tx(em -> {
            Task existingTask = em.find(Task.class, task.getId());
            existingTask.setDone(task.isDone());
            em.merge(existingTask);
            return existingTask;
        });
    }

    /**
     * the method for adding the new task to the data base.
     *
     * @param task - the new task
     * @return - the task with its id in the data base.
     */
    public Task addTask(Task task) {
        return this.tx(em -> em.merge(task));
    }

    /**
     * the wrapper.
     *
     * @param command - the hibernate command
     * @param <T>     - the type of the returning value
     * @return
     */
    private <T> T tx(final Function<EntityManager, T> command) {
        final EntityManager em = this.emf.createEntityManager();
        final EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            T result = command.apply(em);
            tx.commit();
            return result;
        } catch (final Exception e) {
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
