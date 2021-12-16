package com.murmylo.epam.cinema.servlets;

import com.murmylo.epam.cinema.db.entity.Session;
import com.murmylo.epam.cinema.service.SessionService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class IndexServlet extends CommonServlet {

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

        List<Session> sessions = null;
        try {
            sessions = sessionService.findAllLocalized(lan);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            req.getSession().setAttribute("errormsg", e.getMessage());
            sendRedirect("error.jsp", resp);
        }

        String filter = req.getParameter("filter");
        String range = req.getParameter("range");

        if (filter != null) {
            req.getSession().setAttribute("filter", filter);
        }

        if (range != null) {
            req.getSession().setAttribute("range", range);
        }

        String sfilter = (String) req.getSession().getAttribute("filter");
        String srange = (String) req.getSession().getAttribute("range");

        if (srange != null)
            sessions = sessionService.filterSessions(srange, sessions);

        if (sfilter != null) {
            sessions = sessionService.sortSessions(sfilter, sessions);
        }

        int noOfRecords = sessions != null ? sessions.size() : 0;
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        List<Session> sessions1 = sessionService.findN((page - 1) * recordsPerPage,
                recordsPerPage, Objects.requireNonNull(sessions));

        req.setAttribute("currentPage", page);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("sessions", sessions1);

        logger.info("ends");

        forward("index.jsp", req, resp);
    }
}
