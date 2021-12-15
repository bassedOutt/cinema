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

@WebServlet("/delete_movie")
public class DeleteMovieServlet extends CommonServlet {
    private final Logger logger = Logger.getLogger(DeleteMovieServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        MovieService service = new MovieService();
        try {
            service.delete(new Movie(id));
        } catch (SQLException e) {
            logger.error(e.getMessage());
            req.getSession().setAttribute("errormsg", "failed delete movie");
            sendRedirect("error.jsp", resp);
        }

        sendRedirect(req.getContextPath(), resp);

    }
}
