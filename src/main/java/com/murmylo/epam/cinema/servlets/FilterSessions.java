package com.murmylo.epam.cinema.servlets;

import com.murmylo.epam.cinema.db.entity.Session;
import com.murmylo.epam.cinema.service.SessionService;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/filter")
public class FilterSessions extends HttpServlet {
    private final Logger logger = Logger.getLogger(FilterSessions.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filter = req.getParameter("filter");
        String range = req.getParameter("range");
        String lan = (String) req.getSession().getAttribute("language");

        req.setAttribute("filter",filter);
        req.setAttribute("range",range);

        SessionService sessionService = new SessionService();
        List<Session> sessions = sessionService.findAllLocalized(lan);
        sessions = sessionService.filterSessions(filter,sessions);
        logger.info(sessions);
        req.setAttribute("sessions",sessions);

        try {
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
