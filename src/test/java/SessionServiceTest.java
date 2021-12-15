import com.murmylo.epam.cinema.db.ConnectionPool;
import com.murmylo.epam.cinema.db.dao.SessionDAO;
import com.murmylo.epam.cinema.db.entity.Movie;
import com.murmylo.epam.cinema.db.entity.Session;
import com.murmylo.epam.cinema.service.SessionService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Map;

public class SessionServiceTest {

    private static final HikariConfig config = new HikariConfig();

    @BeforeClass
    public static void init() {
        config.setJdbcUrl("jdbc:mysql://localhost:3306/cinema");
        config.setUsername("root");
        config.setPassword("root_password1!");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        HikariDataSource ds = new HikariDataSource(config);
        new ConnectionPool(ds);
    }

    @Ignore
    @Test
    public void insertSessionSeats() throws SQLException {
        SessionDAO sessionDAO = new SessionDAO();
        Session session = new Session();
        Movie movie = new Movie();
        movie.setLanguage("en");
        movie.setId(1);
        session.setMovie(movie);
        session.setId(1);

        Session session1 = sessionDAO.get(session);
        sessionDAO.getSessionSeats(session1);

    }

    @Test
    public void testTimeCollapse() throws SQLException {
        Session session = new Session();
        session.setDate(Date.valueOf("2021-11-21"));
        session.setStartTime(Time.valueOf("16:40:00"));
        session.setEndTime(Time.valueOf("18:40:00"));

        SessionService sessionService = new SessionService();
        boolean b = false;
        b = sessionService.noTimeOverlap(session);
        Assert.assertTrue(b);
    }

    @Test
    public void testTimeCollapse2() throws SQLException {
        Session session = new Session();
        session.setDate(Date.valueOf("2021-11-21"));
        session.setStartTime(Time.valueOf("12:40:00"));
        session.setEndTime(Time.valueOf("13:40:00"));

        SessionService sessionService = new SessionService();
        boolean b = false;
        b = sessionService.noTimeOverlap(session);
        Assert.assertFalse(b);
    }

    @Test
    public void testTimeCollapse3() throws SQLException {
        Session session = new Session();
        session.setDate(Date.valueOf("2021-11-21"));
        session.setStartTime(Time.valueOf("12:00:00"));
        session.setEndTime(Time.valueOf("13:00:00"));

        SessionService sessionService = new SessionService();
        boolean b = sessionService.noTimeOverlap(session);
        Assert.assertFalse(b);
    }

    @Test
    public void testTimeCollapse4() throws SQLException {
        Session session = new Session();
        session.setDate(Date.valueOf("2021-11-21"));
        session.setStartTime(Time.valueOf("13:01:00"));
        session.setEndTime(Time.valueOf("16:00:00"));

        SessionService sessionService = new SessionService();
        boolean b = false;
        b = sessionService.noTimeOverlap(session);

        Assert.assertFalse(b);
    }

    @Test
    public void testStatistics() throws SQLException {
        SessionService service = new SessionService();
        List<Session> sessionList = service.findAllLocalized("en");
        Map<String, Long> map = service.mapMoviesVisiting(sessionList);
        map.forEach((key, value) -> System.out.println(key + " " + value));
    }
}

