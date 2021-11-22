package com.murmylo.epam.cinema.servlets;

import com.murmylo.epam.cinema.db.entity.Movie;
import com.murmylo.epam.cinema.service.MovieService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class IndexServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        MovieService movieService = new MovieService();
        List<Movie> movies = movieService.findAll();
        System.out.println("here");
        System.out.println(Arrays.toString(movies.toArray()));
        request.setAttribute("movies",movies);
        try {
            request.getRequestDispatcher("index.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
