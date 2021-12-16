package com.murmylo.epam.cinema.servlets.statistics;

import com.murmylo.epam.cinema.db.entity.Session;
import com.murmylo.epam.cinema.service.SessionService;
import com.murmylo.epam.cinema.servlets.CommonServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/statistics")
public class StatisticsServlet extends CommonServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String lan = (String) req.getSession().getAttribute("language");

        SessionService service = new SessionService();
        List<Session> sessions = null;
        try {
            sessions = service.findAllLocalized(lan);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            req.getSession().setAttribute("errormsg", e.getMessage());
            sendRedirect("error.jsp", resp);
        }

        assert sessions != null;
        Map<String, Long> map = service.mapMoviesVisiting(sessions);
        req.setAttribute("map",map);
        try {
            req.getRequestDispatcher("statistics.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }
}
