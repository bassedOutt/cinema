package com.murmylo.epam.cinema.servlets.sorters;

import com.murmylo.epam.cinema.db.entity.Session;
import com.murmylo.epam.cinema.service.SessionService;
import com.murmylo.epam.cinema.servlets.CommonServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/get_session")
public class ViewMovieSession extends CommonServlet {
    private final Logger logger = Logger.getLogger(ViewMovieSession.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("start");
        int movieId = Integer.parseInt(req.getParameter("movie_id"));
        String lan = (String) req.getSession().getAttribute("language");

        req.setAttribute("movie_id", movieId);

        SessionService sessionService = new SessionService();
        List<Session> sessions = null;
        try {
            sessions = sessionService.findAllLocalized(lan);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            req.getSession().setAttribute("errormsg", e.getMessage());
            sendRedirect("error.jsp", resp);
        }

        assert sessions != null;
        sessions = sessionService.findMovieSessions(movieId, sessions);
        req.setAttribute("sessions", sessions);

        forward("index.jsp", req, resp);
        logger.info("end");
    }
}
