package com.murmylo.epam.cinema.db.entity;

public class Pricing implements Entity {
    private int id;
    private String name;
    private double price;

    public Pricing(int id) {
        this.id = id;
    }

    public Pricing() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
