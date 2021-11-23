package com.murmylo.epam.cinema.db.dao;

import com.murmylo.epam.cinema.db.Query;
import com.murmylo.epam.cinema.db.entity.Receipt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiptDAO extends GenericDAO<Receipt>{
    @Override
    protected PreparedStatement updateStatement(Receipt entity, Connection connection) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected PreparedStatement insertStatement(Receipt entity, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.INSERT_RECEIPT);
        preparedStatement.setInt(1,entity.getUserId());
        preparedStatement.setDouble(2,entity.getPrice());
        preparedStatement.setInt(3,entity.getSessionId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement deleteStatement(Receipt entity, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.DELETE_RECEIPT);
        preparedStatement.setInt(1,entity.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getStatement(Receipt entity, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.GET_RECEIPT);
        preparedStatement.setInt(1,entity.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getAllStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(Query.GET_ALL_RECEIPT);
    }

    @Override
    protected Receipt getEntity(ResultSet rs) throws SQLException {
        Receipt receipt = new Receipt();
        receipt.setId(rs.getInt("id"));
        receipt.setSessionId(rs.getInt("session_id"));
        receipt.setPrice(rs.getDouble("price"));
        receipt.setUserId(rs.getInt("user_id"));

        return receipt;
    }
}
