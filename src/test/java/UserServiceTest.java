import com.murmylo.epam.cinema.db.ConnectionPool;
import com.murmylo.epam.cinema.db.entity.User;
import com.murmylo.epam.cinema.service.UserService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class UserServiceTest {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    private static final String JDBC_DRIVER = "org.h2.Driver";
    @BeforeClass
    public static void init(){
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        config.setJdbcUrl("jdbc:h2:~/cinema");
        config.setUsername("sa");
        config.setPassword("");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
        new ConnectionPool(ds);
    }

    @Test
    public void insertAndDeleteUser(){
        String name = "Petro";
        String surname = "Poroshenko";
        String email = "poroshenkoo@mail.com";
        String password = "roshen@roshen.com";

        User user = new User(name,surname,email,password);
        UserService userService = new UserService();

        boolean b = userService.insert(user);

        Assert.assertTrue(b);

        boolean deleted = userService.delete(user);

        Assert.assertTrue(deleted);
    }


    @Test
    public void getUser(){
        String email = "vmurmylo@gmail.com";
        String password = "password_hard";

        User expected = new User(email,password);
        UserService userService = new UserService();

        User actual = userService.get(expected);
        Assert.assertTrue(expected.equals(actual));
    }

    @Test
    public void updateUser(){
        String name = "Jordan";
        String surname = "Peterson";
        String email = "peterson@gmail.com";
        String password = "petersonpass";

        User user = new User(name,surname,email,password);
        UserService userService = new UserService();

        userService.insert(user);

        user.setEmail("peterson@yahoo.com");
        boolean b = userService.update(user);
        userService.delete(user);

        Assert.assertTrue(b);
    }

    @Test
    public void getAllUser(){

        UserService userService = new UserService();

        String name = "Joe";
        String surname = "Rogan";
        String email = "jre@gmail.com";
        String password = "jrepower";

        User user = new User(name,surname,email,password);
        userService.insert(user);

        List<User> userList = userService.findAll();
        User actual = userList.get(userList.size()-1);
        userService.delete(user);

        Assert.assertEquals(user.toString(),actual.toString());

    }
}
