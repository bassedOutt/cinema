package com.murmylo.epam.cinema.service;

import com.murmylo.epam.cinema.db.dao.SessionDAO;
import com.murmylo.epam.cinema.db.entity.Seat;
import com.murmylo.epam.cinema.db.entity.Session;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class SessionService implements IService<Session> {

    private final Logger logger = Logger.getLogger(SessionService.class);

    private final SessionDAO dao = new SessionDAO();

    @Override
    public boolean insert(Session session) throws SQLException {
        try {
            dao.insert(session);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to insert session. Please try again");
        }
        return true;
    }

    @Override
    public boolean update(Session session) throws SQLException {
        try {
            dao.update(session);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to update session. Please try again");
        }
        return true;
    }

    @Override
    public boolean delete(Session session) throws SQLException {
        try {
            dao.delete(session);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to delete session. Please try again");
        }
        return true;
    }

    @Override
    public Session get(Session session) throws SQLException {
        try {
            return dao.get(session);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to get session. Please try again");
        }
    }

    @Override
    public List<Session> findAll() throws SQLException {
        try {
            List<Session> sessions = dao.findAll();
            for (Session session : sessions) {
                this.getSessionSeats(session);
            }
            return sessions;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to get all session. Please try again");
        }
    }

    public List<Session> findAllLocalized(String locale) throws SQLException {
        return findAll().stream().filter(s -> s.getMovie().getLanguage().equals(locale)).collect(Collectors.toList());
    }

    public List<Session> findN(int offset, int n, List<Session> sessions) {
        return sessions.stream().skip(offset).limit(n).collect(Collectors.toList());
    }

    public List<Session> sortSessions(String filter, List<Session> sessions) {
        switch (filter) {
            case "name":
                return sessions.stream().sorted(byName).collect(Collectors.toList());
            case "time": {
                return sessions.stream().sorted(byTime).collect(Collectors.toList());
            }
            case "seats": {
                return sessions.stream().sorted(bySeats.reversed()).collect(Collectors.toList());
            }
        }
        return sessions;
    }

    public List<Session> findMovieSessions(int movieId, List<Session> sessions) {
        return sessions.stream().filter(session -> session.getMovie().getId() == movieId).collect(Collectors.toList());
    }

    public List<Session> filterSessions(String filter, List<Session> sessions) {

        logger.info("start");
        Date begin = Date.valueOf("2021-12-17");
        Date end = Date.valueOf("2021-12-17");
//        Date end = Date.valueOf(LocalDate.now());

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


    public void getSessionSeats(Session session) throws SQLException {
        SeatService seatService = new SeatService();
        List<Seat> seats = seatService.findAll();
        List<Seat> sessionSeats = seats.stream().filter(seat -> seat.getSessionId() == session.getId()).collect(Collectors.toList());
        session.setSeats(sessionSeats);
    }

    public Map<String, Long> mapMoviesVisiting(List<Session> sessions) {
        return sessions.stream()
                .collect(Collectors.groupingBy(s -> s.getMovie().getTitle(),
                        Collectors.mapping(this::mapSessionToCountOfSeats, Collectors.toList())))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> sumCountOfSeatsList(e.getValue())))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).collect(
                        Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }

    public boolean noTimeOverlap(Session s) throws SQLException {
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

    public void createSeats(Session session) {
        try {
            dao.createSeats(session);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    private final Comparator<Session> byName = Comparator.comparing((Session s) -> s.getMovie().getTitle());
    private final Comparator<Session> byTime = (Session s1, Session s2) ->
            s1.getDate().compareTo(s2.getDate()) == 0 ? s1.getStartTime().compareTo(s2.getStartTime()) : s1.getDate().compareTo(s2.getDate());

    private final Comparator<Session> bySeats = Comparator.comparing(Session::getFreeSeats);

    private long mapSessionToCountOfSeats(Session session) {
        return session.getSeats().stream().filter(Seat::isTaken).count();
    }

    private long sumCountOfSeatsList(List<Long> seats) {
        return seats.stream().reduce(0L, Long::sum);
    }

}
