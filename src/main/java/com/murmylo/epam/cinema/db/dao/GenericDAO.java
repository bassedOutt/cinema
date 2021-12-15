package com.murmylo.epam.cinema.db.dao;

import com.murmylo.epam.cinema.db.ConnectionPool;
import com.murmylo.epam.cinema.db.entity.Entity;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDAO<T extends Entity> implements IGenericDAO<T> {

    protected final Logger logger = Logger.getLogger(GenericDAO.class);

    protected abstract PreparedStatement updateStatement(T entity, Connection connection) throws SQLException;

    protected abstract PreparedStatement insertStatement(T entity, Connection connection) throws SQLException;

    protected abstract PreparedStatement deleteStatement(T entity, Connection connection) throws SQLException;

    protected abstract PreparedStatement getStatement(T entity, Connection connection) throws SQLException;

    protected abstract PreparedStatement getAllStatement(Connection connection) throws SQLException;

    protected abstract T getEntity(ResultSet rs) throws SQLException;

    public List<T> findAll() throws SQLException {
        List<T> entities = new ArrayList<>();
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = ConnectionPool.getConnection();
            stmt = getAllStatement(connection);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                T entity = getEntity(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
            throw new SQLException("Can not obtain all entities of " + e.getMessage());
        } finally {
            closeConnections(stmt, connection);
        }
    }

    public boolean update(T entity) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = ConnectionPool.getConnection();
            stmt = updateStatement(entity, connection);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("cannot update " + entity.getClass().getSimpleName());
        } finally {
            closeConnections(stmt, connection);
        }
        return true;
    }

    public boolean delete(T entity) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = ConnectionPool.getConnection();
            stmt = deleteStatement(entity, connection);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Can not delete this " + entity.getClass().getSimpleName() + e.getMessage());
        } finally {
            closeConnections(stmt, connection);
        }
        return true;
    }

    public boolean insert(T entity) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = ConnectionPool.getConnection();
            stmt = insertStatement(entity, connection);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating entity failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating entity failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Can't insert " + entity.getClass().getSimpleName() + e.getMessage());
        } finally {
            closeConnections(stmt, connection);
        }
        return true;
    }

    public T get(T entity) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getConnection();
            stmt = getStatement(entity, connection);
            rs = stmt.executeQuery();
            if (rs.next())
                return getEntity(rs);
        } catch (SQLException e) {
            throw new SQLException("Can't get " + entity.getClass().getSimpleName() + " " + e.getMessage());
        } finally {
            closeConnections(rs, stmt, connection);
        }
        return null;
    }

    protected void closeConnections(AutoCloseable... autoCloseables) {
        for (AutoCloseable autoCloseable : autoCloseables) {
            if (autoCloseable != null) {
                try {
                    autoCloseable.close();
                } catch (Exception e) {
                    logger.error("failed to close connections");
                }
            }
        }
    }

}
