package com.murmylo.epam.cinema.db.entity;

public class Ticket implements Entity {
    private int id;
    private int userId;
    private double price;
    private Session Session;
    private Seat seat;

    public Session getSession() {
        return Session;
    }

    public void setSession(Session session) {
        Session = session;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", userId=" + userId +
                ", price=" + price +
                ", Session=" + Session +
                ", seat=" + seat +
                '}';
    }
}
