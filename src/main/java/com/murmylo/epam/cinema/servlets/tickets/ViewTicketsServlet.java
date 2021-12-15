package com.murmylo.epam.cinema.servlets.tickets;

import com.murmylo.epam.cinema.db.entity.Ticket;
import com.murmylo.epam.cinema.db.entity.User;
import com.murmylo.epam.cinema.service.TicketService;
import com.murmylo.epam.cinema.servlets.CommonServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/tickets")
public class ViewTicketsServlet extends CommonServlet {

    private final Logger logger = Logger.getLogger(ViewTicketsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        String lang = (String) req.getSession().getAttribute("language");

        try {
            TicketService ticketService = new TicketService();
            List<Ticket> ticketList = ticketService.findUserTickets(user, lang);
            req.setAttribute("tickets", ticketList);
            logger.info(ticketList);

            forward("ticket.jsp", req, resp);

        } catch (SQLException e) {
            logger.error(e.getMessage());
            req.getSession().setAttribute("errormsg", e.getMessage());
            sendRedirect("error.jsp", resp);
        }
    }
}
