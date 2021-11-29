package com.murmylo.epam.cinema.servlets.sessions;

import com.murmylo.epam.cinema.db.entity.Movie;
import com.murmylo.epam.cinema.db.entity.Pricing;
import com.murmylo.epam.cinema.db.entity.Session;
import com.murmylo.epam.cinema.service.MovieService;
import com.murmylo.epam.cinema.service.PricingService;
import com.murmylo.epam.cinema.service.SessionService;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

@WebServlet("/movie_edited")
public class SubmitEditServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(SubmitEditServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("starts");

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

        Movie movie1 = movieService.get(movie);

        Time start = Time.valueOf(startTime);
        Time end = Time.valueOf(endTime);
        Date date1 = Date.valueOf(date);

        if (!CanUpdateTime(toDateTime(date1, start), toDateTime(date1, end), movie1.getDuration())) {

            logger.debug("can't update time");
            req.setAttribute("errormessage", "movie session time difference can not be less than it's duration");
            try {
                req.getRequestDispatcher("edit_session?id=" + sessionId).forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
            return;
        }

        SessionService sessionService = new SessionService();
        Session session = new Session(sessionId);

        if (start.before(Time.valueOf("9:00:00"))) {
            req.setAttribute("errormessage", "movies can not start earlier than 9 am");
            try {
                req.getRequestDispatcher("edit_session?id=" + sessionId).forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
            return;
        }

        if (start.after(Time.valueOf("22:00:00"))) {
            req.setAttribute("errormessage", "movies can not start after than 10 pm");
            try {
                req.getRequestDispatcher("edit_session?id=" + sessionId).forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
            return;
        }

        session.setDate(date1);
        session.setStartTime(start);
        session.setEndTime(end);

        session.setMovie(movie1);
        if (!sessionService.noTimeOverlap(session)) {
            logger.debug("can't update time, overlap");
            req.setAttribute("errormessage", "there's already a session going on that time, please consider another time");
            try {
                req.getRequestDispatcher("edit_session?id=" + sessionId).forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
            return;
        }

        PricingService pricingService = new PricingService();
        Pricing pricing = pricingService.get(new Pricing(pricingId));
        session.setPricing(pricing);

        if (session.getId() != 0) {
            sessionService.update(session);
        }
        else{
            sessionService.insert(session);
            sessionService.createSeats(session);
        }

        try {
            resp.sendRedirect(req.getContextPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("ends");
    }

    public DateTime toDateTime(java.sql.Date date, java.sql.Time time) {
        DateTime t = new DateTime(time);
        return new DateTime(date).withTime(t.getHourOfDay(), t.getMinuteOfHour(), t.getSecondOfMinute(), t.getMillisOfSecond());
    }

    private boolean CanUpdateTime(DateTime start, DateTime end, int duration) {

        long diff = end.getMillis() - start.getMillis();
        long diffMinutes = diff / (60 * 1000);

        return diffMinutes > duration;
    }
}
