package com.murmylo.epam.cinema.service;

import com.murmylo.epam.cinema.db.dao.SessionDAO;
import com.murmylo.epam.cinema.db.entity.Movie;
import com.murmylo.epam.cinema.db.entity.Session;

import java.sql.SQLException;
import java.util.List;

public class SessionService implements IService<Session>{

    private SessionDAO dao = new SessionDAO();
    @Override
    public boolean insert(Session session) {
        try {
            dao.insert(session);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Session session) {
        try {
            dao.update(session);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Session session) {
        try {
            dao.delete(session);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Session get(Session session) {
        try {
            Session session1 = dao.get(session);
            return session1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Session> findAll() {
        try {
            List<Session> sessions = dao.findAll();
            return sessions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
