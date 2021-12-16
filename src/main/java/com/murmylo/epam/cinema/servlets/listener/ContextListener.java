package com.murmylo.epam.cinema.servlets.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class ContextListener implements ServletContextListener {

    private final Logger logger = Logger.getLogger(ContextListener.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        List<String> userUrls = new ArrayList<>();
        String path = ctx.getContextPath();
        userUrls.add(path + "/buy_tickets");
        userUrls.add(path + "/tickets");
        userUrls.add(path + "/view_tickets.jsp");

        List<String> adminUrls = new ArrayList<>();
        adminUrls.add(path + "/delete_movie");
        adminUrls.add(path + "/edit_movie");
        adminUrls.add(path + "/movie_submitted");
        adminUrls.add(path + "/delete_session");
        adminUrls.add(path + "/edit_session");
        adminUrls.add(path + "/movie_edited");
        adminUrls.add(path + "/movie_submitted");
        adminUrls.add(path + "/statistics");
        adminUrls.add(path + "/movie_form.jsp");
        adminUrls.add(path + "/session_form.jsp");
        adminUrls.add(path + "/statistics.jsp");

        ctx.setAttribute("adminUrls", adminUrls);
        ctx.setAttribute("userUrls", userUrls);

        logger.info("context initialized");

        logger.debug("user urls: " + userUrls);
        logger.debug("admin urls: " + adminUrls);

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        ctx.removeAttribute("userUrls");
        ctx.removeAttribute("adminUrls");
        logger.info("context destroyed");
    }
}
