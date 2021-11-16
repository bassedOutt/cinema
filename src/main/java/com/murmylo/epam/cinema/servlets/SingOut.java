package com.murmylo.epam.cinema.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign_out")
public class SingOut extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)  {
        request.getSession().invalidate();
        request.removeAttribute("user");
        try {
            response.sendRedirect("/epam_cinema/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
