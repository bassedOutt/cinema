package com.murmylo.epam.cinema.servlets;

import com.murmylo.epam.cinema.db.dao.UserDAO;
import com.murmylo.epam.cinema.db.entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/login")
public class Login extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//
//        UserDAO userDAO = UserDAO.getInstance();
//
//        User user = userDAO.get(new User(email,password));
//
//        request.getSession().setAttribute("user",user);
//        try {
//            response.sendRedirect("/epam_cinema/index.jsp");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
