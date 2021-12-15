package com.murmylo.epam.cinema.service;

import com.murmylo.epam.cinema.db.dao.PricingDAO;
import com.murmylo.epam.cinema.db.entity.Pricing;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class PricingService implements IService<Pricing> {
    private final PricingDAO dao = new PricingDAO();
    private final Logger logger = Logger.getLogger(PricingService.class);

    @Override
    public boolean insert(Pricing pricing) throws SQLException {
        try {
            dao.insert(pricing);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to insert pricing " + pricing.getName() + ". Please try again");
        }
        return true;
    }

    @Override
    public boolean update(Pricing pricing) throws SQLException {
        try {
            dao.update(pricing);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to update pricing " + pricing.getName() + ". Please try again");
        }
        return true;
    }

    @Override
    public boolean delete(Pricing pricing) throws SQLException {
        try {
            dao.delete(pricing);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to delete pricing " + pricing.getName() + ". Please try again");
        }
        return true;
    }

    @Override
    public Pricing get(Pricing pricing) throws SQLException {
        try {
            return dao.get(pricing);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to get pricing " + pricing.getName() + ". Please try again");
        }
    }

    @Override
    public List<Pricing> findAll() throws SQLException {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to get list of pricings. Please try again");
        }
    }
}
