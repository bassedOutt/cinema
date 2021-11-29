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

@WebServlet("/movie_submitted")
public class MovieFormSubmitted extends HttpServlet {
    private final Logger logger = Logger.getLogger(MovieFormSubmitted.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("starts");

        String url = req.getParameter("url");
        int id = Integer.parseInt(req.getParameter("id"));
        int price = Integer.parseInt(req.getParameter("price"));
        int duration = Integer.parseInt(req.getParameter("duration"));

        String title_ua = req.getParameter("title_ua");
        String title_en = req.getParameter("title_en");

        String description_ua = req.getParameter("description_ua");
        String description_en = req.getParameter("description_en");

        logger.debug("id: " + id);
        logger.debug("url: " + url);
        logger.debug("price: " + price);
        logger.debug("duration: " + duration);
        logger.debug("title ua: " + title_ua);
        logger.debug("title en: " + title_en);
        logger.debug("description ua: " + description_ua);
        logger.debug("description en: " + description_en);

        Movie movie = new Movie();
        movie.setId(id);
        movie.setLanguage("ua");
        movie.setTitle(title_ua);
        movie.setPrice(price);
        movie.setDuration(duration);
        movie.setImageUrl(url);
        movie.setDescription(description_ua);

        MovieService movieService = new MovieService();

        if (id == 0) {
            movieService.insert(movie);
            movieService.insertTranslation(movie);

            movie.setLanguage("en");
            movie.setTitle(title_en);
            movie.setDescription(description_en);
            movieService.insertTranslation(movie);

            logger.debug("movie after insert: "+movie);
        } else {
            movieService.update(movie);
            movie.setLanguage("en");
            movie.setTitle(title_en);
            movie.setDescription(description_en);
            movieService.update(movie);
            logger.debug("movie after update: "+movie);

        }

        try {
            logger.info("redirect to index");
            resp.sendRedirect(req.getContextPath() + "/index");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
