package com.murmylo.epam.cinema.db.dao;

import com.murmylo.epam.cinema.db.Query;
import com.murmylo.epam.cinema.db.entity.Ticket;

import java.sql.*;

public class TicketDAO extends GenericDAO<Ticket>{
    @Override
    protected PreparedStatement updateStatement(Ticket entity, Connection connection) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected PreparedStatement insertStatement(Ticket entity, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.INSERT_TICKET, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1,entity.getUserId());
        preparedStatement.setDouble(2,entity.getPrice());
        preparedStatement.setDouble(3,entity.getSeat_id());
        preparedStatement.setInt(4,entity.getSessionId());
        preparedStatement.setInt(5,entity.getMovie_id());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement deleteStatement(Ticket entity, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.DELETE_TICKET);
        preparedStatement.setInt(1,entity.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getStatement(Ticket entity, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.GET_TICKET);
        preparedStatement.setInt(1,entity.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getAllStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(Query.GET_ALL_TICKET);
    }

    @Override
    protected Ticket getEntity(ResultSet rs) throws SQLException {
        Ticket receipt = new Ticket();
        receipt.setId(rs.getInt("id"));
        receipt.setSessionId(rs.getInt("session_id"));
        receipt.setPrice(rs.getDouble("price"));
        receipt.setUserId(rs.getInt("user_id"));

        return receipt;
    }
}
