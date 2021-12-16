package com.murmylo.epam.cinema.servlets.login;

import com.murmylo.epam.cinema.servlets.CommonServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends CommonServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        request.removeAttribute("user");
        sendRedirect(request.getContextPath()+"/index", response);
    }
}
