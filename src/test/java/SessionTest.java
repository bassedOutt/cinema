import com.murmylo.epam.cinema.db.ConnectionPool;
import com.murmylo.epam.cinema.db.dao.SessionDAO;
import com.murmylo.epam.cinema.db.entity.Movie;
import com.murmylo.epam.cinema.db.entity.Session;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

public class SessionTest {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    @BeforeClass
    public static void init(){
        config.setJdbcUrl("jdbc:mysql://localhost:3306/cinema");
        config.setUsername("root");
        config.setPassword("root_password1!");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
        new ConnectionPool(ds);
    }

    @Test
    public void insertSessionSeats(){
        SessionDAO sessionDAO = new SessionDAO();
        Session session = new Session();
        Movie movie = new Movie();
        movie.setLanguage("en");
        movie.setId(1);
        session.setMovie(movie);
        session.setId(1);

        try {
            Session session1 = sessionDAO.get(session);
            sessionDAO.getSessionSeats(session1);
//            System.out.println(session1.getSeats().toArray().toString());
            System.out.println(session1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertSession(){

    }
}
