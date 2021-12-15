import com.murmylo.epam.cinema.db.ConnectionPool;
import com.murmylo.epam.cinema.db.entity.User;
import com.murmylo.epam.cinema.servlets.hashing.Hashing;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class HashingTest {

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
    public void testHash() throws Exception {

        String password = "volodymyrpassword";
        byte[] salt = Hashing.getNewSalt();
        User user = new User("user@gmail.com");

        String encPass = Hashing.getEncryptedPassword(password, salt);
        user.setPassword(encPass);

        Assert.assertTrue(Hashing.authenticatePass(user, password));
    }
}
