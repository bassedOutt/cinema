package com.murmylo.epam.cinema.db.dao;

import com.murmylo.epam.cinema.db.ConnectionPool;
import com.murmylo.epam.cinema.db.Query;
import com.murmylo.epam.cinema.db.entity.Movie;
import com.murmylo.epam.cinema.db.entity.Pricing;
import com.murmylo.epam.cinema.db.entity.Seat;
import com.murmylo.epam.cinema.db.entity.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionDAO extends GenericDAO<Session> {
    @Override
    protected PreparedStatement updateStatement(Session session, Connection connection) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement insertStatement(Session session, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.INSERT_SESSION);
        preparedStatement.setInt(1,session.getMovie().getId());
        preparedStatement.setTime(2,session.getStartTime());
        preparedStatement.setTime(3,session.getEndTime());
        preparedStatement.setDate(4,session.getDate());
        preparedStatement.setInt(5,session.getPricing().getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement deleteStatement(Session session, Connection connection) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement getStatement(Session session, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.GET_SESSION);
        preparedStatement.setString(1,session.getMovie().getLanguage());
        preparedStatement.setInt(2,session.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getAllStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(Query.GET_ALL_SESSION);
    }

    @Override
    protected Session getEntity(ResultSet rs) throws SQLException {
        Session session = new Session();
        session.setId(rs.getInt("s.id"));
        session.setStartTime(rs.getTime("start_time"));
        session.setEndTime(rs.getTime("end_time"));
        session.setSeats_num(rs.getInt("seats_num"));
        session.setDate(rs.getDate("date"));

        Movie movie= new Movie();
        movie.setId(rs.getInt("m.id"));
        movie.setTitle(rs.getString("title"));
        movie.setDuration(rs.getInt("duration"));
        movie.setImageUrl(rs.getString("image_url"));
        movie.setDescription(rs.getString("description"));
        movie.setPrice(rs.getInt("m.price"));
        movie.setStartDate(rs.getDate("start_date"));
        movie.setLanguage(rs.getString("language"));

        Pricing pricing = new Pricing();
        pricing.setId(rs.getInt("p.id"));
        pricing.setName(rs.getString("name"));
        pricing.setPrice(rs.getInt("p.price"));

        session.setPricing(pricing);
        session.setMovie(movie);

        return session;
    }

    public boolean getSessionSeats(Session session) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(Query.GET_SESSION_SEATS);
            preparedStatement.setInt(1,session.getId());
            ResultSet rs = preparedStatement.executeQuery();
            List<Seat> seats = new ArrayList<>();
            SeatDAO seatDAO = new SeatDAO();
            while(rs.next()){
                Seat seat = seatDAO.getEntity(rs);
                seats.add(seat);
            }
            session.setSeats(seats);
            System.out.println(seats);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }


    public boolean createSeats(Session session) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = ConnectionPool.getConnection();
            stmt = connection.prepareStatement(Query.CREATE_SEATS);
            stmt.setInt(1,session.getId());
            stmt.setInt(2,session.getMovie().getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throw new SQLException("Can't insert movie translation" + throwables.getMessage());
        } finally {
            closeConnections(stmt, connection);
        }
    }
}
