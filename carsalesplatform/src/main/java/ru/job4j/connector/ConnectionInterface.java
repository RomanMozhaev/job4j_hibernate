 package ru.job4j.connector;

import ru.job4j.models.Car;
import ru.job4j.models.User;

import java.util.List;

 public interface ConnectionInterface {

    int addUser(User user);

    boolean addCar(Car car);

    List<Car> carsByUser(User user);

    List<Car> allCars();

    User isCredential(User user);

    boolean changeStatus(Car car);

}
