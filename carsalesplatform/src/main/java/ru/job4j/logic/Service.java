package ru.job4j.logic;

import ru.job4j.connector.ConnectionInterface;
import ru.job4j.connector.Connector;
import ru.job4j.models.Car;
import ru.job4j.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * the class for preparing data for processing with data base.
 */
public class Service implements ServiceInterface {

    /**
     * the connector to data base class instance.
     */
    private final ConnectionInterface connector = Connector.getInstance();

    /**
     * the instance of the service class.
     */
    private static final Service INSTANCE = new Service();

    /**
     * the getter of the instance.
     * @return the instance of the service class.
     */
    public static Service getInstance() {
        return INSTANCE;
    }

    /**
     * loads list of all offers from data base.
     * @return the list of offers.
     */
    @Override
    public List<Car> loadTable() {
        List<Car> cars = this.connector.allCars();
        if (cars == null) {
            cars = new ArrayList<>();
        }
        return cars;
    }

    /**
     * checks credentials of the user.
     * @param user - the user.
     * @return user's id or -1, if the user was not found.
     */
    @Override
    public int isCredential(User user) {
        int result = -1;
        User foundUser = this.connector.isCredential(user);
        if (foundUser != null) {
            result = foundUser.getId();
        }
        return result;
    }

    /**
     * loads the offers which tied to the user.
     * @param user - the user who added offers.
     * @return - the list of offers.
     */
    @Override
    public List<Car> loadByUser(User user) {
        List<Car> cars = null;
        if (user.getId() > 0) {
            cars = this.connector.carsByUser(user);
        }
        if (cars == null) {
            cars = new ArrayList<>();
        }
        return cars;
    }

    /**
     * adds new user.
     * @param user the user for adding.
     * @return user's id or -1 if the user was not added to the data base.
     */
    @Override
    public int addUser(User user) {
        return this.connector.addUser(user);
    }

    /**
     * adds a new offer.
     * @param car the offer for car selling.
     * @return true if added; otherwise false.
     */
    @Override
    public boolean addCar(Car car) {
        return this.connector.addCar(car);
    }

    /**
     * changes status of the offer.
     * @param car the offer for car selling.
     * @return true if changed; otherwise false.
     */
    @Override
    public boolean changeStatus(Car car) {
        return this.connector.changeStatus(car);
    }
}
