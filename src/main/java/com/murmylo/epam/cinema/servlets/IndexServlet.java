package com.murmylo.epam.cinema.servlets;

import com.murmylo.epam.cinema.db.entity.Movie;
import com.murmylo.epam.cinema.db.entity.Session;
import com.murmylo.epam.cinema.service.MovieService;
import com.murmylo.epam.cinema.service.SessionService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class IndexServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(IndexServlet.class);
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        String lan = (String) request.getSession().getAttribute("language");
        logger.info("language " +lan);

        if(lan==null) {
            lan = "en";
            request.getSession().setAttribute("language", lan);
        }

        SessionService sessionService = new SessionService();
        List<Session> sessions = sessionService.findAllLocalized(lan);
        request.setAttribute("sessions",sessions);
        logger.info("transferring list of sessions to frontend");

        try {
            request.getRequestDispatcher("index.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
