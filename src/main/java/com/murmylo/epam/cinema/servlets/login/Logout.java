package com.murmylo.epam.cinema.servlets.login;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)  {
        request.getSession().invalidate();
        request.removeAttribute("user");
        try {
            response.sendRedirect(request.getContextPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
