package com.murmylo.epam.cinema.servlets.tickets;

import com.murmylo.epam.cinema.db.entity.Ticket;
import com.murmylo.epam.cinema.db.entity.User;
import com.murmylo.epam.cinema.service.TicketService;
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
public class ViewTicketsServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(ViewTicketsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        String lang = (String) req.getSession().getAttribute("language");

        try {
            TicketService ticketService = new TicketService();
            List<Ticket> ticketList = ticketService.findUserTickets(user,lang);
            req.setAttribute("tickets",ticketList);
            logger.info(ticketList);

            try {
                req.getRequestDispatcher("/ticket.jsp").forward(req,resp);
                logger.info("transferred request to servlet: "+ViewTicketsServlet.class.getSimpleName());
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
