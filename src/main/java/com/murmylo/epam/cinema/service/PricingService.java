package com.murmylo.epam.cinema.service;

import com.murmylo.epam.cinema.db.dao.PricingDAO;
import com.murmylo.epam.cinema.db.entity.Pricing;
import java.sql.SQLException;
import java.util.List;

public class PricingService implements IService<Pricing>{
    private final PricingDAO dao = new PricingDAO();

    @Override
    public boolean insert(Pricing pricing) {
        try {
            dao.insert(pricing);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Pricing pricing) {
        try {
            dao.update(pricing);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Pricing pricing) {
        try {
            dao.delete(pricing);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Pricing get(Pricing pricing) {
        try {
            return dao.get(pricing);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Pricing> findAll() {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
