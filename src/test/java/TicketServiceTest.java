import com.murmylo.epam.cinema.db.ConnectionPool;
import com.murmylo.epam.cinema.db.entity.Ticket;
import com.murmylo.epam.cinema.db.entity.User;
import com.murmylo.epam.cinema.service.TicketService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class TicketServiceTest {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    @BeforeClass
    public static void init() {
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
    public void TicketServiceTest() {
        TicketService ticketService = new TicketService();
        User user = new User();
        user.setId(19);

        try {
            List<Ticket> tickets = ticketService.findUserTickets(user, "ua");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
