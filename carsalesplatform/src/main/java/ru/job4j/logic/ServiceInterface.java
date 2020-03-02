package ru.job4j.logic;

import ru.job4j.models.Car;
import ru.job4j.models.User;

import java.util.List;

public interface ServiceInterface {

    List<Car> loadTable();

    int isCredential(User user);

    List<Car> loadByUser(User user);

    int addUser(User user);

    boolean addCar(Car car);

    boolean changeStatus(Car car);
}
