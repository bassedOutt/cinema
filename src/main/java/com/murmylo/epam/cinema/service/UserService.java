package com.murmylo.epam.cinema.service;

import com.murmylo.epam.cinema.db.dao.UserDAO;
import com.murmylo.epam.cinema.db.entity.User;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class UserService implements IService<User> {

    private final UserDAO dao = new UserDAO();

    private final Logger logger = Logger.getLogger(UserService.class);

    @Override
    public boolean insert(User user) throws SQLException {
        try {
            dao.insert(user);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to insert user " + user.getEmail() + ". Please try again");
        }
        return true;
    }

    @Override
    public boolean update(User user) throws SQLException {
        try {
            dao.update(user);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to update user " + user.getEmail() + ". Please try again");
        }
        return true;
    }

    @Override
    public boolean delete(User user) throws SQLException {
        try {
            dao.delete(user);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to delete user " + user.getEmail() + ". Please try again");
        }
        return true;
    }

    @Override
    public User get(User user) throws SQLException {
        try {
            return dao.get(user);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to get user " + user.getEmail() + ". Please try again");
        }
    }

    @Override
    public List<User> findAll() throws SQLException {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to get users list. Please try again");
        }
    }

}
