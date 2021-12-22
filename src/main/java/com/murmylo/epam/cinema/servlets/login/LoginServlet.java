package com.murmylo.epam.cinema.servlets.login;
import com.murmylo.epam.cinema.db.entity.User;
import com.murmylo.epam.cinema.service.UserService;
import com.murmylo.epam.cinema.servlets.CommonServlet;
import com.murmylo.epam.cinema.servlets.hashing.Hashing;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends CommonServlet {
    private final Logger logger = Logger.getLogger(LoginServlet.class);

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        logger.info("start");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserService userService = new UserService();
        User user = null;
        try {
            user = userService.get(new User(email));
        } catch (SQLException e) {
            logger.error(e.getMessage());
            request.getSession().setAttribute("errormsg", e.getMessage());
            sendRedirect("error.jsp", response);
        }

        if (Hashing.authenticatePass(Objects.requireNonNull(user), password)) {
            request.getSession().setAttribute("user", user);
        } else {
            request.setAttribute("error", "Failed to authenticate user");
            try {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                logger.error(e.getMessage());
            }
        }

        logger.info("User " + user.getId() + " logged out");
        sendRedirect(request.getContextPath(), response);

        logger.info("start");

    }

}
