package com.murmylo.epam.cinema.service;

import com.murmylo.epam.cinema.db.dao.SeatDAO;
import com.murmylo.epam.cinema.db.dao.SessionDAO;
import com.murmylo.epam.cinema.db.entity.Seat;

import java.sql.SQLException;
import java.util.List;

public class SeatSession implements IService<Seat>{
    private SeatDAO dao = new SeatDAO();
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
            dao.get(seat);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Seat> findAll() {
        return null;
    }
}
