package com.murmylo.epam.cinema.db.dao;

import com.murmylo.epam.cinema.db.Query;
import com.murmylo.epam.cinema.db.entity.User;

import java.sql.*;

public class UserDAO extends GenericDAO<User> {

    @Override
    protected PreparedStatement insertStatement(User user, Connection connection) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(Query.INSERT_USER, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getSurname());
        stmt.setString(3, user.getEmail());
        stmt.setString(4, user.getPassword());
        return stmt;
    }

    @Override
    protected PreparedStatement getStatement(User user, Connection connection) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(Query.GET_USER);
        stmt.setString(1, user.getEmail());
        return stmt;
    }

    @Override
    protected PreparedStatement getAllStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(Query.GET_ALL_USER);
    }

    @Override
    protected PreparedStatement updateStatement(User user, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.UPDATE_USER);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setInt(5, user.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement deleteStatement(User user, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.DELETE_USER);
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, user.getPassword());
        return preparedStatement;
    }

    @Override
    protected User getEntity(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setAdmin(rs.getBoolean("is_admin"));
        return user;
    }


}
