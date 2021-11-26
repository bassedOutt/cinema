package com.murmylo.epam.cinema.db.entity;

public class Seat implements Entity {
    private int id;
    private int row;
    private int seatNumber;
    private boolean isVip;
    private boolean isTaken;
    private int sessionId;
    private int movieId;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getSeat(){
        return row*10+ seatNumber -10;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        this.isVip = vip;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        this.isTaken = taken;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", row=" + row +
                ", number=" + seatNumber +
                ", isVip=" + isVip +
                ", isTaken=" + isTaken +
                ", sessionId=" + sessionId +
                ", movieId=" + movieId +
                '}';
    }
}
