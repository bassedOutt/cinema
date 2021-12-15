package com.murmylo.epam.cinema.servlets.tickets;

import com.murmylo.epam.cinema.db.entity.Ticket;
import com.murmylo.epam.cinema.db.entity.Seat;
import com.murmylo.epam.cinema.db.entity.Session;
import com.murmylo.epam.cinema.db.entity.User;
import com.murmylo.epam.cinema.service.SeatService;
import com.murmylo.epam.cinema.service.TicketService;
import com.murmylo.epam.cinema.servlets.CommonServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet("/buy_tickets")
public class BuyTicketsServlet extends CommonServlet {

    private final Logger logger = Logger.getLogger(BuyTicketsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("start");
        List<Integer> seatNumbers = new ArrayList<>();
        final int nOfSeats = 50;
        final int rowLength = 10;

        for (int i = 0; i < nOfSeats; i++) {
            String param = req.getParameter("ticket" + i);
            if (param != null)
                seatNumbers.add(i);
        }

        Session session = (Session) req.getSession().getAttribute("movieSession");

        SeatService seatService = new SeatService();
        List<Seat> seats = null;
        try {
            seats = seatService.findAll();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            req.getSession().setAttribute("errormsg", e.getMessage());
            sendRedirect("error.jsp", resp);
        }
        List<Seat> ticketSeats = new ArrayList<>();

        //check if seats are not taken
        for (Integer seatNumber : seatNumbers) {
            int row = seatNumber / rowLength + 1;
            int number = seatNumber % rowLength == 0 ? rowLength : seatNumber % rowLength;
            for (Seat seat : Objects.requireNonNull(seats)) {
                if (seat.getSessionId() == session.getId() && seat.getRow() == row && seat.getSeatNumber() == number) {
                    if (seat.isTaken()) {
                        req.setAttribute("errormessage", "Sorry, this seat was already taken by another user");
                        forward("view_tickets.jsp", req, resp);
                    }
                    ticketSeats.add(seat);
                }
            }
        }

        double standardPrice = (double) req.getSession().getAttribute("standardPrice");
        double vipPrice = (double) req.getSession().getAttribute("vipPrice");

        TicketService ticketService = new TicketService();
        User user = (User) req.getSession().getAttribute("user");
        //update seats
        for (Seat ticketSeat : ticketSeats) {
            ticketSeat.setTaken(true);
            try {
                seatService.update(ticketSeat);
            } catch (SQLException e) {
                logger.error(e.getMessage());
                req.getSession().setAttribute("errormsg", e.getMessage());
                sendRedirect("error.jsp", resp);
            }

            Ticket ticket = new Ticket();
            if (ticketSeat.isVip())
                ticket.setPrice(vipPrice);
            else
                ticket.setPrice(standardPrice);
            ticket.setSession(session);
            ticket.setUserId(user.getId());

            ticket.setSeat(ticketSeat);
            try {
                ticketService.insert(ticket);
            } catch (SQLException e) {
                logger.error(e.getMessage());
                req.getSession().setAttribute("errormsg", e.getMessage());
                sendRedirect("error.jsp", resp);
            }
        }

        forward("/tickets", req, resp);

    }
}
