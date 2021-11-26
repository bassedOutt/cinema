package com.murmylo.epam.cinema.service;

import com.murmylo.epam.cinema.db.dao.UserDAO;
import com.murmylo.epam.cinema.db.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService implements IService<User>{

    private final UserDAO dao = new UserDAO();

    @Override
    public boolean insert(User user) {
        try {
            dao.insert(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(User user) {
        try {
            dao.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(User user) {
        try {
            dao.delete(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public User get(User user) {
        try {
            return dao.get(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
