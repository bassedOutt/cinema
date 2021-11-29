package com.murmylo.epam.cinema.servlets.tickets;

import com.murmylo.epam.cinema.db.entity.Ticket;
import com.murmylo.epam.cinema.db.entity.Seat;
import com.murmylo.epam.cinema.db.entity.Session;
import com.murmylo.epam.cinema.db.entity.User;
import com.murmylo.epam.cinema.service.SeatService;
import com.murmylo.epam.cinema.service.TicketService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/buy_tickets")
public class BuyTicketsServlet extends HttpServlet {

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
        List<Seat> seats = seatService.findAll();
        List<Seat> ticketSeats = new ArrayList<>();

        //check if seats are not taken
        for (int i = 0; i < seatNumbers.size(); i++) {
            int row = seatNumbers.get(i) / rowLength + 1;
            int number = seatNumbers.get(i) % rowLength == 0 ? rowLength : seatNumbers.get(i) % rowLength;
            for (int j = 0; j < seats.size(); j++) {
                Seat seat = seats.get(j);
                if (seat.getSessionId() == session.getId() && seat.getRow() == row && seat.getSeatNumber() == number) {
                    if (seat.isTaken()) {
                        req.setAttribute("errormessage", "Sorry, this seat was already taken by another user");
                        try {
                            req.getRequestDispatcher("view_tickets.jsp").forward(req, resp);
                            logger.info("end");
                        } catch (ServletException | IOException e) {
                            e.printStackTrace();
                        }
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
        for (int i = 0; i < ticketSeats.size(); i++) {
            ticketSeats.get(i).setTaken(true);
            seatService.update(ticketSeats.get(i));

            Ticket ticket = new Ticket();
            if (ticketSeats.get(i).isVip())
                ticket.setPrice(vipPrice);
            else
                ticket.setPrice(standardPrice);
            ticket.setSession( session);
            ticket.setUserId(user.getId());

            ticket.setSeat(ticketSeats.get(i));
            ticketService.insert(ticket);
        }

        try {
            req.getRequestDispatcher("/tickets").forward(req,resp);
            logger.info("transferred request to servlet: "+ViewTicketsServlet.class.getSimpleName());
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
