package com.murmylo.epam.cinema.servlets;

import com.murmylo.epam.cinema.db.entity.Session;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        String lan = (String) req.getSession().getAttribute("language");
        if(lan==null) {
            lan = "en";
            req.getSession().setAttribute("language", lan);
        }

        SessionService sessionService = new SessionService();
        List<Session> sessions = sessionService.findAllLocalized(lan);
        req.setAttribute("sessions",sessions);

        try {
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
