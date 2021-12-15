package com.murmylo.epam.cinema.servlets.movie;

import com.murmylo.epam.cinema.db.entity.Movie;
import com.murmylo.epam.cinema.service.MovieService;
import com.murmylo.epam.cinema.servlets.CommonServlet;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@WebServlet("/movie_submitted")
public class MovieFormSubmitted extends CommonServlet {
    private final Logger logger = Logger.getLogger(MovieFormSubmitted.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("starts");

        String url = req.getParameter("url");
        int id = Integer.parseInt(req.getParameter("id"));
        int price = Integer.parseInt(req.getParameter("price"));
        int duration = Integer.parseInt(req.getParameter("duration"));

        String title_ua = req.getParameter("title_ua");
        String title_en = req.getParameter("title_en");

        String description_ua = req.getParameter("description_ua");
        String description_en = req.getParameter("description_en");

        Movie movie = new Movie();
        movie.setId(id);
        movie.setLanguage("ua");
        movie.setTitle(title_ua);
        movie.setPrice(price);
        movie.setDuration(duration);
        movie.setImageUrl(url);
        movie.setDescription(description_ua);

        MovieService movieService = new MovieService();

        //insert
        if (id == 0) {
            try {
                movieService.insert(movie);
                movieService.insertTranslation(movie);
                movieService.insertTranslation(movie);
                movie.setLanguage("en");
                movie.setTitle(title_en);
                movie.setDescription(description_en);
                logger.debug("movie after insert: " + movie);

            } catch (SQLException e) {
                logger.error(e.getMessage());
                req.getSession().setAttribute("errormsg", e.getMessage());
                sendRedirect("error.jsp", resp);
                return;
            }

            //update
        } else {
            try {
                movieService.update(movie);
                movie.setLanguage("en");
                movie.setTitle(title_en);
                movie.setDescription(description_en);
                movieService.update(movie);
                logger.debug("movie after update: " + movie);

            } catch (SQLException e) {
                logger.error(e.getMessage());
                req.getSession().setAttribute("errormsg", e.getMessage());
                sendRedirect("error.jsp", resp);
                return;
            }

        }

        sendRedirect(req.getContextPath() + "/index", resp);
    }
}
