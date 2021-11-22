package com.murmylo.epam.cinema.db.entity;

public class Seat implements Entity {
    private int id;
    private int row;
    private int number;
    private boolean isVip;
    private int receiptId;
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

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        this.isVip = vip;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
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
                ", number=" + number +
                ", isVip=" + isVip +
                ", receiptId=" + receiptId +
                ", isTaken=" + isTaken +
                ", sessionId=" + sessionId +
                ", movieId=" + movieId +
                '}';
    }
}
