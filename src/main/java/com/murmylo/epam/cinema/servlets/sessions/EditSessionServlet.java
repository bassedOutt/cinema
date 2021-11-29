package com.murmylo.epam.cinema.servlets.sessions;

import com.murmylo.epam.cinema.db.entity.Movie;
import com.murmylo.epam.cinema.db.entity.Pricing;
import com.murmylo.epam.cinema.db.entity.Session;
import com.murmylo.epam.cinema.service.MovieService;
import com.murmylo.epam.cinema.service.PricingService;
import com.murmylo.epam.cinema.service.SessionService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/edit_session")
public class EditSessionServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(EditSessionServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("starts");

        int id = 0;
        Session session = new Session();
        String lang = (String) req.getSession().getAttribute("language");

        MovieService movieService = new MovieService();
        List<Movie> movies = movieService.findAllLocale(lang);
        req.setAttribute("movies", movies);

        PricingService pricingService = new PricingService();
        List<Pricing> pricingList = pricingService.findAll();
        req.setAttribute("pricings", pricingList);

        if (!(req.getParameter("id") == null)) {
            id = Integer.parseInt(req.getParameter("id"));
            session.setId(id);
            session.setMovie(new Movie(lang));

            SessionService sessionService = new SessionService();
            Session session1 = sessionService.get(session);
            req.setAttribute("session1", session1);
            logger.debug("session: " + session1);

        } else {
            session.setId(0);
            req.setAttribute("session1", session);
        }

        try {
            req.getRequestDispatcher("session_form.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        logger.info("ends");
    }

}
