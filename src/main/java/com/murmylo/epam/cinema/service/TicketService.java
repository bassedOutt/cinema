package com.murmylo.epam.cinema.service;

import com.murmylo.epam.cinema.db.dao.TicketDAO;
import com.murmylo.epam.cinema.db.entity.Seat;
import com.murmylo.epam.cinema.db.entity.Session;
import com.murmylo.epam.cinema.db.entity.Ticket;
import com.murmylo.epam.cinema.db.entity.User;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class TicketService implements IService<Ticket> {
    private final TicketDAO dao = new TicketDAO();

    private final Logger logger = Logger.getLogger(TicketService.class);

    @Override
    public boolean insert(Ticket receipt) throws SQLException {
        try {
            dao.insert(receipt);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to insert receipt " + receipt.getId() + ". Please try again");
        }
        return true;
    }

    @Override
    public boolean update(Ticket receipt) throws SQLException {
        try {
            dao.update(receipt);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to update receipt " + receipt.getId() + ". Please try again");
        }
        return true;
    }

    @Override
    public boolean delete(Ticket receipt) throws SQLException {
        try {
            dao.delete(receipt);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to delete receipt " + receipt.getId() + ". Please try again");
        }
        return true;
    }

    @Override
    public Ticket get(Ticket receipt) throws SQLException {
        try {
            return dao.get(receipt);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to get receipt " + receipt.getId() + ". Please try again");
        }
    }

    public void getFull(Ticket ticket, String locale) throws SQLException {
        SessionService service = new SessionService();
        Session movieSession = service.findAllLocalized(locale).stream().filter(session -> session.getId() == ticket.getSession().getId()).findFirst().get();

        SeatService seatService = new SeatService();
        Seat movieSeat = seatService.findAll().stream().filter(seat -> seat.getId() == ticket.getSeat().getId()).findFirst().get();

        ticket.setSeat(movieSeat);
        ticket.setSession(movieSession);
    }

    @Override
    public List<Ticket> findAll() throws SQLException {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to get receipts. Please try again");
        }
    }

    public List<Ticket> findAllFull(String locale) throws SQLException {
        try {
            List<Ticket> tickets = dao.findAll();
            for (Ticket ticket : tickets) {
                getFull(ticket, locale);
            }
            return tickets;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Failed to get receipts. Please try again");
        }
    }

    public List<Ticket> findUserTickets(User user, String locale) throws SQLException {

        return this.findAllFull(locale).stream().filter(ticket -> ticket.getUserId() == user.getId()).collect(Collectors.toList());
    }

}

