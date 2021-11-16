package com.murmylo.epam.cinema.servlets;

import com.murmylo.epam.cinema.db.dao.UserDAO;
import com.murmylo.epam.cinema.db.entity.User;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/sign_up")
public class SignUp extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(name, surname, email, password);

        UserDAO userDAO = UserDAO.getInstance();
        boolean b = userDAO.insert(user);

        if (b) {
            try {
                response.sendRedirect("/epam_cinema/index.jsp");
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