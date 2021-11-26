package com.murmylo.epam.cinema.servlets.movie;

import com.murmylo.epam.cinema.db.entity.Movie;
import com.murmylo.epam.cinema.service.MovieService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/movies")
public class ShowMoviesServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(ShowMoviesServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("start");
        MovieService movieService = new MovieService();
        String lang = (String) req.getSession().getAttribute("language");

        List<Movie> movies = movieService.findAllLocale(lang);
        req.setAttribute("movies", movies);

        try {
            req.getRequestDispatcher("movies.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        logger.info("end");
    }
}
