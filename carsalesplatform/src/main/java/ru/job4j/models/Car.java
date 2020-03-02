package ru.job4j.models;

import javax.persistence.*;

/**
 * the model of the offer for car selling.
 */
@Entity
@Table(name = "car_tickets")
public class Car {
    /**
     * unique id.
     */
    @Id
    @GeneratedValue
    private int id;
    /**
     * car type.
     */
    @Column(nullable = false)
    private String type;
    /**
     * car brand.
     */
    @Column(nullable = false)
    private String brand;
    /**
     * car brand.
     */
    @Column(nullable = false)
    private String model;
    /**
     * the usage of the vehicle.
     */
    @Column(nullable = false)
    private int usage;
    /**
     * year of the manufacture.
     */
    @Column(nullable = false)
    private int year;
    /**
     * additional information.
     */
    @Column(nullable = false)
    private String description;
    /**
     * the price in RUB.
     */
    @Column(nullable = false)
    private long price;
    /**
     * the path to the picture file.
     */
    @Column(nullable = false)
    private String picture;
    /**
     * the status of the offer; true if sold;
     */
    @Column(nullable = false)
    private boolean sold;
    /**
     * the user who added this offer.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "id"))
    private User user;

    public Car() {
    }

    public Car(String type, String brand, String model, int usage, int year, String description, long price) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.usage = usage;
        this.year = year;
        this.description = description;
        this.price = price;
        this.picture = "";
        this.sold = false;
        this.user = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage) {
        this.usage = usage;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}