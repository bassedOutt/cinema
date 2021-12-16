package com.murmylo.epam.cinema.servlets.sessions;

import com.murmylo.epam.cinema.db.entity.Session;
import com.murmylo.epam.cinema.service.SessionService;
import com.murmylo.epam.cinema.servlets.CommonServlet;
import org.apache.log4j.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@WebServlet("/delete_session")
public class DeleteSessionServlet extends CommonServlet {

    private final Logger logger = Logger.getLogger(DeleteSessionServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        logger.debug("here");
        int id = Integer.parseInt(req.getParameter("id"));
        logger.debug("id: "+ id);
        SessionService service = new SessionService();
        try {
            service.delete(new Session(id));
        } catch (SQLException e) {
            logger.error(e.getMessage());
            req.getSession().setAttribute("errormsg", e.getMessage());
            sendRedirect("error.jsp", resp);
        }

        sendRedirect(req.getContextPath(), resp);
    }
}
