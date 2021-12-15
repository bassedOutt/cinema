package com.murmylo.epam.cinema.db.dao;

import com.murmylo.epam.cinema.db.Query;
import com.murmylo.epam.cinema.db.entity.Movie;
import com.murmylo.epam.cinema.db.ConnectionPool;

import java.sql.*;
import java.util.Objects;

public class MovieDAO extends GenericDAO<Movie> {

    @Override
    protected PreparedStatement updateStatement(Movie movie, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.UPDATE_MOVIE);
        preparedStatement.setString(1, movie.getTitle());
        preparedStatement.setInt(2, movie.getDuration());
        preparedStatement.setString(3, movie.getImageUrl());
        preparedStatement.setString(4, movie.getDescription());
        preparedStatement.setInt(5, movie.getPrice());
        preparedStatement.setInt(6, movie.getId());
        preparedStatement.setString(7, movie.getLanguage());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement insertStatement(Movie movie, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.INSERT_MOVIE, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, movie.getDuration());
        preparedStatement.setString(2, movie.getImageUrl());
        preparedStatement.setInt(3, movie.getPrice());
        return preparedStatement;
    }

    public boolean insertTranslation(Movie movie) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = ConnectionPool.getConnection();
            stmt = Objects.requireNonNull(connection).prepareStatement(Query.INSERT_MOVIE_TRANSLATION);
            stmt.setInt(1, movie.getId());
            stmt.setString(2, movie.getLanguage());
            stmt.setString(3, movie.getTitle());
            stmt.setString(4, movie.getDescription());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new SQLException("Can't insert movie translation " + e.getMessage());
        } finally {
            closeConnections(stmt, connection);
        }

    }

    @Override
    protected PreparedStatement deleteStatement(Movie movie, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.DELETE_MOVIE);
        preparedStatement.setInt(1, movie.getId());
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
        return connection.prepareStatement(Query.GET_ALL_MOVIE);
    }

    public Movie getLocale(Movie movie) throws SQLException {
        Connection connection;
        PreparedStatement preparedStatement;
        connection = ConnectionPool.getConnection();
        preparedStatement = Objects.requireNonNull(connection).prepareStatement(Query.GET_MOVIE_LOCALE);
        preparedStatement.setInt(1, movie.getId());
        preparedStatement.setString(2, movie.getLanguage());

        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next())
            return getEntity(rs);
        return null;
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
        movie.setLanguage(rs.getString("language"));
        return movie;
    }
}

