package com.murmylo.epam.cinema.service;

import com.murmylo.epam.cinema.db.dao.MovieDAO;
import com.murmylo.epam.cinema.db.entity.Movie;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

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
            return dao.get(movie);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Movie> findAll() {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Movie> findAllLocale(String locale){
        return findAll().stream().filter(movie -> movie.getLanguage().equals(locale)).collect(Collectors.toList());
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
