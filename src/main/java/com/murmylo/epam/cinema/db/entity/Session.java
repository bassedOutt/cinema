package com.murmylo.epam.cinema.db.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class Session implements Entity {
    private int id;
    private Movie movie;
    private Time startTime;
    private Time endTime;
    private int seats_num;
    private Date date;
    private Pricing pricing;
    private List<Seat> seats;

    public Session(int sessionId) {
        this.id = sessionId;
    }

    public Session(){}
    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getSeats_num() {
        return seats_num;
    }

    public void setSeats_num(int seats_num) {
        this.seats_num = seats_num;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", movie=" + movie +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", seats_num=" + seats_num +
                ", date=" + date +
                ", pricing=" + pricing +
                '}';
    }



    public long getFreeSeats(){
        return seats.stream().filter(seat -> seat.isTaken()==false).count();
    }
}
