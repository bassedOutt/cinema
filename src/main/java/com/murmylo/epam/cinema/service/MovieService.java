package com.murmylo.epam.cinema.service;

import com.murmylo.epam.cinema.db.dao.MovieDAO;
import com.murmylo.epam.cinema.db.entity.Movie;

import java.sql.SQLException;
import java.util.List;

public class MovieService implements IService<Movie>{
    MovieDAO dao = new MovieDAO();
    @Override
    public boolean insert(Movie movie) {
        try {
            dao.insert(movie);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Movie movie) {
        try {
            dao.update(movie);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Movie movie) {
        try {
            dao.delete(movie);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Movie get(Movie movie) {
        try {
            Movie movie1 = dao.get(movie);
            return movie1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Movie> findAll() {
        try {
            List<Movie> movies = dao.findAll();
            return movies;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertTranslation(Movie movie){
        try {
            dao.insertTranslation(movie);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
