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

@WebServlet("/edit_movie")
public class EditMovieServlet extends CommonServlet {
    private final Logger logger = Logger.getLogger(EditMovieServlet.class);

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

            try {
                Movie movie_en = movieService.getLocale(movieEn);
                Movie movie_ua = movieService.getLocale(movieUa);
                req.setAttribute("movie_en", movie_en);
                req.setAttribute("movie_ua", movie_ua);

            } catch (SQLException e) {
                logger.error(e.getMessage());
                req.getSession().setAttribute("errormsg", "failed to obtain movie from database. Please try again");
                sendRedirect("error.jsp", resp);
            }

        }
        forward("movie_form.jsp", req, resp);

    }
}
