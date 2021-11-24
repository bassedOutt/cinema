package com.murmylo.epam.cinema.service;

import com.murmylo.epam.cinema.db.dao.SessionDAO;
import com.murmylo.epam.cinema.db.entity.Movie;
import com.murmylo.epam.cinema.db.entity.Session;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Session> findAllLocalized(String locale){
       return findAll().stream().filter(s->s.getMovie().getLanguage().equals(locale)).collect(Collectors.toList());
    }

    public List<Session> filterSessions(String filter, List<Session> sessions){
        switch (filter){
            case "name":
                 return (List<Session>) sessions.stream().sorted(byName).collect(Collectors.toList());
            case "time":{
                return (List<Session>) sessions.stream().sorted(byTime).collect(Collectors.toList());
            }
            case "seats":{
                return (List<Session>) sessions.stream().sorted(bySeats).collect(Collectors.toList());
            }
        }
        return sessions;
    }

    private Comparator byName = Comparator.comparing((Session s) -> s.getMovie().getTitle());
    private Comparator byTime = Comparator.comparing((Session::getStartTime));
    private Comparator bySeats = Comparator.comparing((Session s) -> s.getNumOfFreeSeats());

}
