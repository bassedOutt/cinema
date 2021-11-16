import com.murmylo.epam.cinema.db.ConnectionPool;
import com.murmylo.epam.cinema.db.dao.UserDAO;
import com.murmylo.epam.cinema.db.entity.User;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class UserDAOTest {
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
        UserDAO dao = UserDAO.getInstance();

        boolean b = dao.insert(user);

        Assert.assertTrue(b);

        boolean deleted = dao.delete(user);

        Assert.assertTrue(deleted);
    }


    @Test
    public void getUser(){
        String email = "vmurmylo@gmail.com";
        String password = "password_hard";

        User expected = new User(email,password);
        UserDAO userDAO = UserDAO.getInstance();

        User actual = userDAO.get(expected);
        Assert.assertTrue(expected.equals(actual));
    }

    @Test
    public void updateUser(){
        String name = "Jordan";
        String surname = "Peterson";
        String email = "peterson@gmail.com";
        String password = "petersonpass";

        User user = new User(name,surname,email,password);
        UserDAO dao = UserDAO.getInstance();

        dao.insert(user);

        user.setEmail("peterson@yahoo.com");
        boolean b = dao.update(user);
        dao.delete(user);

        Assert.assertTrue(b);
    }

    @Test
    public void getAllUser(){

        UserDAO userDAO = UserDAO.getInstance();

        String name = "Joe";
        String surname = "Rogan";
        String email = "jre@gmail.com";
        String password = "jrepower";

        User user = new User(name,surname,email,password);
        userDAO.insert(user);

        List<User> userList = userDAO.findAll();
        User actual = userList.get(userList.size()-1);
        userDAO.delete(user);

        Assert.assertEquals(user.toString(),actual.toString());

    }
}
