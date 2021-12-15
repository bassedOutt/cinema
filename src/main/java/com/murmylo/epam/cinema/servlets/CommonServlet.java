package com.murmylo.epam.cinema.servlets;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class CommonServlet extends HttpServlet {

    protected final Logger logger = Logger.getLogger(CommonServlet.class);

    public void sendRedirect(String path, HttpServletResponse response) {
        try {
            response.sendRedirect(path);
        } catch (IOException ex) {
            logger.error("ex");
        }
    }

    public void forward(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher(path).forward(request, response);
        } catch (ServletException | IOException e) {
            request.getSession().setAttribute("errormsg", "failed to forward your request. Please try again");
            sendRedirect("error.jsp", response);
        }
    }

}
