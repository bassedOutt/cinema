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

@WebServlet("/edit_movie")
public class EditMovieServlet extends HttpServlet {
    private Logger logger = Logger.getLogger(EditMovieServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null) {
            req.setAttribute("movie_en", new Movie());
            req.setAttribute("movie_ua", new Movie());
        } else {
            int id = Integer.parseInt(req.getParameter("id"));
            logger.debug("id: " + id);

            MovieService movieService = new MovieService();

            Movie movieEn = new Movie();
            movieEn.setId(id);
            movieEn.setLanguage("en");

            Movie movieUa = new Movie();
            movieUa.setId(id);
            movieUa.setLanguage("ua");

            logger.debug(movieService.getLocale(movieEn));
            logger.debug(movieService.getLocale(movieUa));

            req.setAttribute("movie_en", movieService.getLocale(movieEn));
            req.setAttribute("movie_ua", movieService.getLocale(movieUa));
        }
        try {
            req.getRequestDispatcher("movie_form.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
