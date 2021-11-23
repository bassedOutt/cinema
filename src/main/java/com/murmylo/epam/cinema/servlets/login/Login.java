package com.murmylo.epam.cinema.servlets.login;

import com.murmylo.epam.cinema.db.entity.User;
import com.murmylo.epam.cinema.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserService userService = new UserService();

        User user = userService.get(new User(email,password));

        request.getSession().setAttribute("user",user);
        try {
            response.sendRedirect(request.getContextPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
