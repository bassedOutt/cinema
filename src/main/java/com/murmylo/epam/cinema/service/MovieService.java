package com.murmylo.epam.cinema.service;

import com.murmylo.epam.cinema.db.dao.MovieDAO;
import com.murmylo.epam.cinema.db.entity.Movie;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class MovieService implements IService<Movie> {
    MovieDAO dao = new MovieDAO();
    private final Logger logger = Logger.getLogger(MovieService.class);

    @Override
    public boolean insert(Movie movie) throws SQLException {
        try {
            dao.insert(movie);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to insert movie " + movie.getTitle() + ". Please try again");
        }
        return true;
    }

    @Override
    public boolean update(Movie movie) throws SQLException {
        try {
            dao.update(movie);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to update movie " + movie.getTitle() + ". Please try again");
        }
        return true;
    }

    @Override
    public boolean delete(Movie movie) throws SQLException {
        try {
            dao.delete(movie);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to delete movie " + movie.getTitle() + ". Please try again");
        }
        return true;
    }

    public Movie getLocale(Movie movie) throws SQLException {
        try {
            return dao.getLocale(movie);
        } catch (SQLException e) {
            throw new SQLException("Failed to get movie " + movie.getTitle() + ". Please try again");
        }
    }

    @Override
    public Movie get(Movie movie) throws SQLException {
        return dao.get(movie);
    }

    @Override
    public List<Movie> findAll() throws SQLException {
        return dao.findAll();
    }

    public List<Movie> findAllLocale(String locale) throws SQLException {
        return findAll().stream().filter(movie -> movie.getLanguage().equals(locale)).collect(Collectors.toList());
    }

    public boolean insertTranslation(Movie movie) throws SQLException {
        try {
            dao.insertTranslation(movie);
            return true;
        } catch (SQLException e) {
            throw new SQLException("Failed to insert translation for movie " + movie.getTitle() + ". Please try again");
        }
    }
}
