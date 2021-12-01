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

        logger.info("starts");
        String lan = (String) req.getSession().getAttribute("language");
        if (lan == null) {
            lan = "en";
            req.getSession().setAttribute("language", lan);
        }

        int page = 1;
        SessionService sessionService = new SessionService();
        int recordsPerPage = 4;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        int noOfRecords = sessionService.findAllLocalized(lan).size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        List<Session> sessions = sessionService.findNLocalized((page-1)*recordsPerPage,
                recordsPerPage,lan);

        req.setAttribute("currentPage", page);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("sessions", sessions);

        logger.info("Sessions: " + sessions);
        try {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        logger.info("ends");
    }
}
