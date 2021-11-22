package com.murmylo.epam.cinema.servlets.login;

import com.murmylo.epam.cinema.db.entity.User;
import com.murmylo.epam.cinema.service.UserService;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/sign_up")
public class Signup extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(name, surname, email, password);

        UserService userService = new UserService();
        boolean b = userService.insert(user);

        if (b) {
            try {
                response.sendRedirect(request.getContextPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.getOutputStream().print("can't sig up");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}