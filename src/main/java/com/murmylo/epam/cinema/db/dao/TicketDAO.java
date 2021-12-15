package com.murmylo.epam.cinema.db.dao;

import com.murmylo.epam.cinema.db.Query;
import com.murmylo.epam.cinema.db.entity.Movie;
import com.murmylo.epam.cinema.db.entity.Seat;
import com.murmylo.epam.cinema.db.entity.Session;
import com.murmylo.epam.cinema.db.entity.Ticket;

import java.sql.*;

public class TicketDAO extends GenericDAO<Ticket> {
    @Override
    protected PreparedStatement updateStatement(Ticket entity, Connection connection) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected PreparedStatement insertStatement(Ticket entity, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.INSERT_TICKET, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, entity.getUserId());
        preparedStatement.setDouble(2, entity.getPrice());
        preparedStatement.setDouble(3, entity.getSeat().getId());
        preparedStatement.setInt(4, entity.getSession().getId());
        preparedStatement.setInt(5, entity.getSession().getMovie().getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement deleteStatement(Ticket entity, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.DELETE_TICKET);
        preparedStatement.setInt(1, entity.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getStatement(Ticket entity, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.GET_TICKET);
        preparedStatement.setInt(1, entity.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getAllStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(Query.GET_ALL_TICKET);
    }

    @Override
    protected Ticket getEntity(ResultSet rs) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getInt("id"));
        ticket.setPrice(rs.getDouble("price"));
        ticket.setUserId(rs.getInt("user_id"));

        Session session = new Session(rs.getInt("seat_session_id"));
        Movie movie = new Movie(rs.getInt("seat_movie_id"));
        session.setMovie(movie);

        Seat seat = new Seat(rs.getInt("seat_id"));

        ticket.setSession(session);
        ticket.setSeat(seat);

        return ticket;
    }
}
