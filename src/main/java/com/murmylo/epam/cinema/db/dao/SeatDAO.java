package com.murmylo.epam.cinema.db.dao;

import com.murmylo.epam.cinema.db.Query;
import com.murmylo.epam.cinema.db.entity.Seat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatDAO extends GenericDAO<Seat>{
    @Override
    protected PreparedStatement updateStatement(Seat entity, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.UPDATE_SEAT);
        preparedStatement.setBoolean(1,entity.isTaken());
        preparedStatement.setInt(2,entity.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement insertStatement(Seat entity, Connection connection) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected PreparedStatement deleteStatement(Seat entity, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.DELETE_SEAT);
        preparedStatement.setInt(1,entity.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getStatement(Seat entity, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.GET_SEAT);
        preparedStatement.setInt(1,entity.getId());
        return  preparedStatement;
    }

    @Override
    protected PreparedStatement getAllStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(Query.GET_ALL_SEAT);
    }

    @Override
    protected Seat getEntity(ResultSet rs) throws SQLException {
        Seat seat = new Seat();
        seat.setId(rs.getInt("id"));
        seat.setRow(rs.getInt("nrow"));
        seat.setSeatNumber(rs.getInt("number"));
        seat.setTaken(rs.getBoolean("is_taken"));
        seat.setVip(rs.getBoolean("is_vip"));
        seat.setMovieId(rs.getInt("movie_id"));
        seat.setSessionId(rs.getInt("session_id"));
        return seat;
    }
}
