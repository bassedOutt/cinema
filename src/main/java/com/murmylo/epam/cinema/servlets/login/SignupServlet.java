package com.murmylo.epam.cinema.servlets.login;

import com.murmylo.epam.cinema.db.entity.User;
import com.murmylo.epam.cinema.service.UserService;
import com.murmylo.epam.cinema.servlets.CommonServlet;
import com.murmylo.epam.cinema.servlets.hashing.Hashing;
import org.apache.log4j.Logger;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

@WebServlet("/sign_up")
public class SignupServlet extends CommonServlet {

    private final Logger logger = Logger.getLogger(SignupServlet.class);

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        logger.info("start");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(name, surname, email, password);

        byte[] salt = Hashing.getNewSalt();

        try {
            user.setPassword(Hashing.getEncryptedPassword(password, salt));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.error("failed to encrypt password");
            request.setAttribute("errormsg", "failed to register user, please try again");
        }

        UserService userService = new UserService();
        try {
            userService.insert(user);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            request.getSession().setAttribute("errormsg", e.getMessage());
            sendRedirect("error.jsp", response);
        }

        logger.info("end");

    }

}