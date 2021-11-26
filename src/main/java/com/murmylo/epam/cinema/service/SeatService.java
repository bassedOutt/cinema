package com.murmylo.epam.cinema.service;

import com.murmylo.epam.cinema.db.dao.SeatDAO;
import com.murmylo.epam.cinema.db.entity.Seat;

import java.sql.SQLException;
import java.util.List;

public class SeatService implements IService<Seat>{
    private final SeatDAO dao = new SeatDAO();
    @Override
    public boolean insert(Seat seat) {
        try {
            dao.insert(seat);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Seat seat) {
        try {
            dao.update(seat);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Seat seat) {
        try {
            dao.delete(seat);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Seat get(Seat seat) {
        try {
            return dao.get(seat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Seat> findAll() {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
