package com.murmylo.epam.cinema.servlets.movie;

import com.murmylo.epam.cinema.db.entity.Movie;
import com.murmylo.epam.cinema.service.MovieService;
import com.murmylo.epam.cinema.servlets.CommonServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/movies")
public class ShowMoviesServlet extends CommonServlet {

    private final Logger logger = Logger.getLogger(ShowMoviesServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("start");
        MovieService movieService = new MovieService();
        String lang = (String) req.getSession().getAttribute("language");

        List<Movie> movies = null;
        try {
            movies = movieService.findAllLocale(lang);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            req.getSession().setAttribute("errormsg", "failed to obtain movies, please try again." +
                    "If you keep getting this error, please try again later");
            sendRedirect("error.jsp", resp);
        }

        req.setAttribute("movies", movies);

        forward("movies.jsp", req, resp);

        logger.info("end");
    }
}
