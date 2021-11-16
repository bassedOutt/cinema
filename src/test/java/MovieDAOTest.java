import com.murmylo.epam.cinema.db.ConnectionPool;
import com.murmylo.epam.cinema.db.dao.MovieDAO;

import com.murmylo.epam.cinema.db.entity.Movie;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class MovieDAOTest {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    private static final String JDBC_DRIVER = "org.h2.Driver";

    @BeforeClass
    public static void init() {
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
    public void insertMovie() {
        Movie movie = new Movie();
        movie.setImageUrl("https://m.media-amazon.com/images/I/41xApuX9fML._SY445_.jpg");
        movie.setPrice(145);
        movie.setDuration(157);
        movie.setLanguage("en");
        movie.setTitle("Zodiac");
        movie.setDescription("Between 1968 and 1983, a San Francisco cartoonist becomes an amateur detective obsessed with tracking down the Zodiac Killer, an unidentified individual who terrorizes Northern California with a killing spree.");

        MovieDAO dao = new MovieDAO();
        boolean b = dao.insert(movie);
        Assert.assertTrue(b);

        b = dao.insertTranslation(movie);
        Assert.assertTrue(b);

        movie.setLanguage("ua");
        movie.setTitle("Зодіак");
        movie.setDescription("У період з 1968 по 1983 рік карикатурист із Сан-Франциско стає детективом-любителем, одержимим пошуком Зодіакального вбивці, невідомої особи, яка тероризує Північну Каліфорнію за допомогою вбивств.");
        b = dao.insertTranslation(movie);
        Assert.assertTrue(b);

        dao.delete(movie);
    }

    @Test
    public void getMovie(){
        Movie movie = new Movie();
        movie.setId(11);
        movie.setTitle("Fight Club");
        movie.setLanguage("en");
        movie.setDuration(139);
        movie.setPrice(132);
        movie.setImageUrl("https://m.media-amazon.com/images/M/MV5BMmEzNTkxYjQtZTc0MC00YTVjLTg5ZTEtZWMwOWVlYzY0NWIwXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_FMjpg_UX1000_.jpg");
        movie.setDescription("An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more.");

        MovieDAO movieDao = new MovieDAO();
        Movie actual = movieDao.get(movie);

        Assert.assertEquals(movie.toString(),actual.toString());
    }

    @Test
    public void getAllMovies(){

        MovieDAO movieDao = new MovieDAO();
        Movie movie = new Movie();
        movie.setImageUrl("https://m.media-amazon.com/images/I/41xApuX9fML._SY445_.jpg");
        movie.setPrice(145);
        movie.setDuration(157);
        movie.setLanguage("en");
        movie.setTitle("Zodiac");
        movie.setDescription("Between 1968 and 1983, a San Francisco cartoonist becomes an amateur detective obsessed with tracking down the Zodiac Killer, an unidentified individual who terrorizes Northern California with a killing spree.");

        movieDao.insert(movie);
        movieDao.insertTranslation(movie);

        List<Movie> userList = movieDao.findAll();
        Movie actual = userList.get(userList.size()-1);
        movieDao.delete(movie);

        Assert.assertEquals(movie.toString(),actual.toString());
    }

    //h2 does not support update with join
    /*@Test
    public void updateMovie(){
        MovieDao movieDao = new MovieDao();
        Movie movie = new Movie();
        movie.setImageUrl("https://m.media-amazon.com/images/I/41xApuX9fML._SY445_.jpg");
        movie.setPrice(145);
        movie.setDuration(157);
        movie.setLanguage("en");
        movie.setTitle("Zodiac");
        movie.setDescription("Between 1968 and 1983, a San Francisco cartoonist becomes an amateur detective obsessed with tracking down the Zodiac Killer, an unidentified individual who terrorizes Northern California with a killing spree.");

        movieDao.insert(movie);
        movie.setLanguage("ua");
        movie.setPrice(155);
        movieDao.update(movie);

        boolean b = movieDao.update(movie);
        movieDao.delete(movie);

        Assert.assertTrue(b);
    }*/

}