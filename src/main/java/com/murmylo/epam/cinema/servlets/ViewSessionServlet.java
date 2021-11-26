package com.murmylo.epam.cinema.servlets;

import com.murmylo.epam.cinema.db.entity.Movie;
import com.murmylo.epam.cinema.db.entity.Session;
import com.murmylo.epam.cinema.service.SessionService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/movie_session")
public class ViewSessionServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(ViewSessionServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("start");
        int id = Integer.parseInt(req.getParameter("id"));
        String lang = (String) req.getSession().getAttribute("language");
        logger.info("session id: " +id);

        Session session = new Session();
        session.setId(id);
        session.setMovie(new Movie(lang));

        SessionService service = new SessionService();
        Session session1 = service.get(session);
        service.getSessionSeats(session1);

        final int vip = 40;
        double standardPrice = session1.getMovie().getPrice()+session1.getPricing().getPrice();
        double vipPrice = standardPrice+vip;

        req.getSession().setAttribute("movieSession",session1);
        req.getSession().setAttribute("standardPrice",standardPrice);
        req.getSession().setAttribute("vipPrice",vipPrice);
        logger.info("movie session: "+session1);

        try {
            req.getRequestDispatcher("session.jsp").forward(req,resp);
            logger.info("end");
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
