package com.murmylo.epam.cinema.servlets.filter;

import com.murmylo.epam.cinema.db.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    private Logger logger = Logger.getLogger(AuthFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();
        logger.info("Requested Resource::"+uri);

        User user = (User) req.getSession().getAttribute("user");
        if(user==null)
            resp.sendRedirect(req.getContextPath()+"/login");
    }
}
