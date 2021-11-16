package com.murmylo.epam.cinema.db.dao;

import com.murmylo.epam.cinema.db.Query;
import com.murmylo.epam.cinema.db.entity.Movie;
import com.murmylo.epam.cinema.db.ConnectionPool;

import java.sql.*;

public class MovieDAO extends GenericDAO<Movie> {

    @Override
    protected PreparedStatement updateStatement(Movie movie, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.UPDATE_MOVIE);
        preparedStatement.setString(1, movie.getTitle());
        preparedStatement.setString(2, movie.getLanguage());
        preparedStatement.setInt(3, movie.getDuration());
        preparedStatement.setString(4, movie.getImageUrl());
        preparedStatement.setDate(5, movie.getStartDate());
        preparedStatement.setString(6, movie.getDescription());
        preparedStatement.setInt(7, movie.getPrice());
        preparedStatement.setInt(8, movie.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement insertStatement(Movie movie, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.INSERT_MOVIE, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, movie.getDuration());
        preparedStatement.setString(2, movie.getImageUrl());
        preparedStatement.setInt(3, movie.getPrice());
        preparedStatement.setDate(4, movie.getStartDate());
        return preparedStatement;
    }

    public boolean insertTranslation(Movie movie) {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = ConnectionPool.getConnection();
            stmt = connection.prepareStatement(Query.INSERT_MOVIE_TRANSLATION);
            stmt.setInt(1, movie.getId());
            stmt.setString(2, movie.getLanguage());
            stmt.setString(3, movie.getTitle());
            stmt.setString(4, movie.getDescription());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnections(stmt, connection);
        }

    }

    @Override
    protected PreparedStatement deleteStatement(Movie movie, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.DELETE_MOVIE);
        preparedStatement.setString(1, movie.getTitle());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getStatement(Movie movie, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.GET_MOVIE);
        preparedStatement.setString(1, movie.getTitle());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getAllStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.GET_ALL_MOVIE);
        return preparedStatement;
    }

    @Override
    protected Movie getEntity(ResultSet rs) throws SQLException {
        Movie movie = new Movie();
        movie.setId(rs.getInt("id"));
        movie.setTitle(rs.getString("title"));
        movie.setDuration(rs.getInt("duration"));
        movie.setImageUrl(rs.getString("image_url"));
        movie.setDescription(rs.getString("description"));
        movie.setPrice(rs.getInt("price"));
        movie.setStartDate(rs.getDate("start_date"));
        movie.setLanguage(rs.getString("language"));
        return movie;
    }
}

