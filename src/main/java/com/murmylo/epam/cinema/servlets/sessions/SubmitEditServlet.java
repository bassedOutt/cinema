package com.murmylo.epam.cinema.servlets.sessions;

import com.murmylo.epam.cinema.db.entity.Movie;
import com.murmylo.epam.cinema.db.entity.Pricing;
import com.murmylo.epam.cinema.db.entity.Session;
import com.murmylo.epam.cinema.service.MovieService;
import com.murmylo.epam.cinema.service.PricingService;
import com.murmylo.epam.cinema.service.SessionService;
import com.murmylo.epam.cinema.servlets.CommonServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Objects;

import static com.murmylo.epam.cinema.Utils.CanUpdateTime;
import static com.murmylo.epam.cinema.Utils.toDateTime;

@WebServlet("/movie_edited")
public class SubmitEditServlet extends CommonServlet {
    private final Logger logger = Logger.getLogger(SubmitEditServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("starts");

        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("failed to set encoding to utf-8");
        }

        String language = (String) req.getSession().getAttribute("language");
        String title = req.getParameter("movie_title");
        int sessionId = Integer.parseInt(req.getParameter("session_id"));
        int pricingId = Integer.parseInt(req.getParameter("pricing_id"));

        String date = req.getParameter("date");
        String startTime = req.getParameter("start_time");
        String endTime = req.getParameter("end_time");

        if (sessionId == 0) {
            startTime += ":00";
            endTime += ":00";
        }

        logger.debug("date: " + date);
        logger.debug("startTime: " + startTime);
        logger.debug("end time: " + endTime);
        logger.debug("movie title: " + title);
        logger.debug("session id: " + sessionId);
        logger.debug("pricing id: " + pricingId);

        MovieService movieService = new MovieService();
        Movie movie = new Movie(language);
        movie.setTitle(title);

        Movie movie1 = null;
        try {
            movie1 = movieService.get(movie);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            req.getSession().setAttribute("errormsg", "failed to get the desired movie");
            sendRedirect("error.jsp", resp);
        }

        Time start = Time.valueOf(startTime);
        Time end = Time.valueOf(endTime);
        Date date1 = Date.valueOf(date);

        if (!CanUpdateTime(toDateTime(date1, start), toDateTime(date1, end), Objects.requireNonNull(movie1).getDuration())) {
            logger.debug("can't update time");
            req.getSession().setAttribute("errormessage", "movie session time difference can not be less than it's duration");
            sendRedirect("edit_session?id=" + sessionId, resp);
            return;
        }

        SessionService sessionService = new SessionService();
        Session session = new Session(sessionId);

        if (start.before(Time.valueOf("9:00:00"))) {
            req.getSession().setAttribute("errormessage", "movies can not start earlier than 9 am");
            sendRedirect("edit_session?id=" + sessionId,  resp);
            return;
        }

        if (start.after(Time.valueOf("22:00:00"))) {
            req.getSession().setAttribute("errormessage", "movies can not start after than 10 pm");
            sendRedirect("edit_session?id=" + sessionId,  resp);
            return;
        }

        session.setDate(date1);
        session.setStartTime(start);
        session.setEndTime(end);

        session.setMovie(movie1);
        try {
            if (!sessionService.noTimeOverlap(session)) {
                logger.debug("can't update time, overlap");
                req.getSession().setAttribute("errormessage", "there's already a session going on that time, please consider another time");
                sendRedirect("edit_session?id=" + sessionId,  resp);
                return;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            req.getSession().setAttribute("errormsg", e.getMessage());
            sendRedirect("error.jsp", resp);
        }

        PricingService pricingService = new PricingService();
        Pricing pricing = null;
        try {
            pricing = pricingService.get(new Pricing(pricingId));
        } catch (SQLException e) {
            logger.error(e.getMessage());
            req.getSession().setAttribute("errormsg", e.getMessage());
            sendRedirect("error.jsp", resp);
        }
        session.setPricing(pricing);

        if (session.getId() != 0) {
            try {
                sessionService.update(session);
            } catch (SQLException e) {
                logger.error(e.getMessage());
                req.getSession().setAttribute("errormsg", e.getMessage());
                sendRedirect("error.jsp", resp);
            }
        } else {
            try {
                sessionService.insert(session);
            } catch (SQLException e) {
                logger.error(e.getMessage());
                req.getSession().setAttribute("errormsg", e.getMessage());
                sendRedirect("error.jsp", resp);
            }
            sessionService.createSeats(session);
        }

        sendRedirect(req.getContextPath(), resp);
        logger.info("ends");
    }

}
