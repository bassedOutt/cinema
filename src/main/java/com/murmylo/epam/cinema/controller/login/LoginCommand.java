package com.murmylo.epam.cinema.controller.login;

import com.murmylo.epam.cinema.controller.Command;
import com.murmylo.epam.cinema.db.dao.UserDAO;
import com.murmylo.epam.cinema.db.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDAO = UserDAO.getInstance();

        User user = userDAO.get(new User(email,password));

        request.getSession().setAttribute("user",user);
        try {
            response.sendRedirect("/epam_cinema/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
