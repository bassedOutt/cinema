package com.murmylo.epam.cinema.service;

import com.murmylo.epam.cinema.db.dao.SeatDAO;
import com.murmylo.epam.cinema.db.dao.SessionDAO;
import com.murmylo.epam.cinema.db.entity.Seat;
import com.murmylo.epam.cinema.db.entity.Session;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SessionService implements IService<Session> {

    private final Logger logger = Logger.getLogger(SessionService.class);

    private final SessionDAO dao = new SessionDAO();

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
            return dao.get(session);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Session> findAll() {
        try {
            List<Session> sessions = dao.findAll();
            for (Session session : sessions) {
                this.getSessionSeats(session);
            }
            return sessions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Session> findAllLocalized(String locale) {
        return findAll().stream().filter(s -> s.getMovie().getLanguage().equals(locale)).collect(Collectors.toList());
    }

    public List<Session> findNLocalized(int offset,int n,String locale){
        return this.findAllLocalized(locale).stream().skip(offset).limit(n).collect(Collectors.toList());
    }

    public List<Session> sortSessions(String filter, List<Session> sessions) {
        switch (filter) {
            case "name":
                return sessions.stream().sorted(byName).collect(Collectors.toList());
            case "time": {
                return sessions.stream().sorted(byTime).collect(Collectors.toList());
            }
            case "seats": {
                return sessions.stream().sorted(bySeats).collect(Collectors.toList());
            }
        }
        return sessions;
    }

    public List<Session> findMovieSessions(int movieId, List<Session> sessions) {
        return sessions.stream().filter(session -> session.getMovie().getId() == movieId).collect(Collectors.toList());
    }

    public List<Session> filterSessions(String filter, List<Session> sessions) {

//        Date date = new Date(new java.util.Date().getTime());
        logger.info("start");
        Date begin = Date.valueOf("2021-11-21");
        Date end = Date.valueOf("2021-11-21");

        switch (filter) {
            case "today":
                break;
            case "tomorrow": {
                begin = addDays(end, 1);
                end = addDays(end, 1);
                break;
            }
            case "week": {
                end = addDays(end, 7);
                break;
            }
            default:
                return sessions;
        }

        final Date begin1 = begin;
        final Date end1 = end;

        logger.info("filtering sessions in range: " + filter);
        logger.info("end");
        return sessions.stream().filter(session -> !(session.getDate().before(begin1)) && !(session.getDate().after(end1))).collect(Collectors.toList());
    }

    private final Comparator<Session> byName = Comparator.comparing((Session s) -> s.getMovie().getTitle());
    private final Comparator<Session> byTime = Comparator.comparing((Session::getStartTime));
    private final Comparator<Session> bySeats = Comparator.comparing(Session::getFreeSeats);

    public void getSessionSeats(Session session) {
        SeatService seatService = new SeatService();
        List<Seat> seats = seatService.findAll();
        List<Seat> sessionSeats = seats.stream().filter(seat -> seat.getSessionId() == session.getId()).collect(Collectors.toList());
        session.setSeats(sessionSeats);
    }

    public Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }

    public boolean noTimeOverlap(Session s) {
        List<Session> sessions = this.findAll().stream()
                .filter(session1 -> session1.getDate().equals(s.getDate()))
                .filter(session -> session.getId() != s.getId())
                .collect(Collectors.toList());
        return sessions.stream()
                .allMatch(s1 -> !(s1.getStartTime().after(s.getStartTime())
                        && s1.getEndTime().before(s.getStartTime()))
                        && !(s1.getStartTime().before(s.getEndTime())
                        && s1.getEndTime().after(s.getEndTime()))
                        && s1.getStartTime().compareTo(s.getStartTime()) != 0 && s1.getEndTime().compareTo(s.getEndTime()) != 0);
    }

    public boolean createSeats(Session session) {
        try {
            return dao.createSeats(session);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

}
