package com.murmylo.epam.cinema.service;

import com.murmylo.epam.cinema.db.dao.SeatDAO;
import com.murmylo.epam.cinema.db.entity.Seat;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class SeatService implements IService<Seat> {

    private final Logger logger = Logger.getLogger(SeatService.class);
    private final SeatDAO dao = new SeatDAO();

    @Override
    public boolean insert(Seat seat) throws SQLException {
        try {
            dao.insert(seat);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to insert seat " + seat.getId() + ". Please try again");
        }
        return true;
    }

    @Override
    public boolean update(Seat seat) throws SQLException {
        try {
            dao.update(seat);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to update seat " + seat.getId() + ". Please try again");
        }
        return true;
    }

    @Override
    public boolean delete(Seat seat) throws SQLException {
        try {
            dao.delete(seat);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to delete seat " + seat.getId() + ". Please try again");
        }
        return true;
    }

    @Override
    public Seat get(Seat seat) throws SQLException {
        try {
            return dao.get(seat);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to get seat " + seat.getId() + ". Please try again");
        }
    }

    @Override
    public List<Seat> findAll() throws SQLException {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to get all seats. Please try again");
        }
    }

}
