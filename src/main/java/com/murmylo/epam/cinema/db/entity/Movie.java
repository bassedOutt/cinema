package com.murmylo.epam.cinema.db.entity;

import java.sql.Date;

public class Movie implements Entity {
    private int id;
    private int duration;
    private String imageUrl;
    private int price;
    private Date startDate;
    private String title;
    private String description;
    private String language;

    public Movie(int id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Movie() {
    }

    public Movie(String language) {
        this.language = language;
    }

    public Movie(Movie movie) {
        this.id = movie.id;
        this.duration = movie.duration;
        this.imageUrl = movie.imageUrl;
        this.price = movie.price;
        this.startDate = movie.startDate;
        this.title = movie.title;
        this.description = movie.description;
        this.language = movie.language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", duration=" + duration +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", startDate=" + startDate +
                ", name='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
