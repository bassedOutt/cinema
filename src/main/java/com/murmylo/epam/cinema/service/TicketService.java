package com.murmylo.epam.cinema.service;

import com.murmylo.epam.cinema.db.dao.TicketDAO;
import com.murmylo.epam.cinema.db.entity.Receipt;

import java.sql.SQLException;
import java.util.List;

public class ReceiptService implements IService<Receipt> {
    private TicketDAO dao = new TicketDAO();

    @Override
    public boolean insert(Receipt receipt) {
        try {
            dao.insert(receipt);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Receipt receipt) {
        try {
            dao.update(receipt);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Receipt receipt) {
        try {
            dao.delete(receipt);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Receipt get(Receipt receipt) {
        try {
            return dao.get(receipt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Receipt> findAll() {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

