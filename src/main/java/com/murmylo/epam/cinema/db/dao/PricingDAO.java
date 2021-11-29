package com.murmylo.epam.cinema.db.dao;

import com.murmylo.epam.cinema.db.Query;
import com.murmylo.epam.cinema.db.entity.Pricing;

import java.sql.*;

public class PricingDAO extends GenericDAO<Pricing> {
    @Override
    protected PreparedStatement updateStatement(Pricing pricing, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.UPDATE_PRICING);
        preparedStatement.setString(1, pricing.getName());
        preparedStatement.setDouble(2, pricing.getPrice());
        preparedStatement.setInt(3, pricing.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement insertStatement(Pricing pricing, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.INSERT_PRICING, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, pricing.getName());
        preparedStatement.setDouble(2, pricing.getPrice());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement deleteStatement(Pricing pricing, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.DELETE_PRICING);
        preparedStatement.setInt(1, pricing.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getStatement(Pricing pricing, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.GET_PRICING);
        preparedStatement.setInt(1, pricing.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getAllStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(Query.GET_ALL_PRICING);
    }

    @Override
    protected Pricing getEntity(ResultSet rs) throws SQLException {
        Pricing pricing = new Pricing();
        pricing.setId(rs.getInt("id"));
        pricing.setPrice(rs.getDouble("price"));
        pricing.setName(rs.getString("name"));
        return pricing;
    }
}
