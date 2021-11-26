package com.murmylo.epam.cinema.servlets;


import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/changelang")
public class ChangeLanguage  extends HttpServlet {
    final Logger logger = Logger.getLogger(ChangeLanguage.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("start");
        String lang =  req.getParameter("language");
        logger.info("language set to "+lang);
        req.getSession().setAttribute("language",lang);


        try {
            resp.sendRedirect(req.getContextPath()+"/index");
            logger.info("end");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
