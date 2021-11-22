import com.murmylo.epam.cinema.db.ConnectionPool;
import com.murmylo.epam.cinema.db.entity.Movie;
import com.murmylo.epam.cinema.service.MovieService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class MovieServiceTest {

    private static final HikariConfig config = new HikariConfig();
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
        HikariDataSource ds = new HikariDataSource(config);
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

        MovieService movieService = new MovieService();
        boolean b = movieService.insert(movie);
        Assert.assertTrue(b);

        b = movieService.insertTranslation(movie);
        Assert.assertTrue(b);

        movie.setLanguage("ua");
        movie.setTitle("Зодіак");
        movie.setDescription("У період з 1968 по 1983 рік карикатурист із Сан-Франциско стає детективом-любителем, одержимим пошуком Зодіакального вбивці, невідомої особи, яка тероризує Північну Каліфорнію за допомогою вбивств.");
        b = movieService.insertTranslation(movie);
        Assert.assertTrue(b);

        movieService.delete(movie);
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

        MovieService movieService = new MovieService();
        Movie actual = movieService.get(movie);

        Assert.assertEquals(movie.toString(),actual.toString());
    }

    @Test
    public void getAllMovies(){

        MovieService movieService = new MovieService();
        Movie movie = new Movie();
        movie.setImageUrl("https://m.media-amazon.com/images/I/41xApuX9fML._SY445_.jpg");
        movie.setPrice(145);
        movie.setDuration(157);
        movie.setLanguage("en");
        movie.setTitle("Zodiac");
        movie.setDescription("Between 1968 and 1983, a San Francisco cartoonist becomes an amateur detective obsessed with tracking down the Zodiac Killer, an unidentified individual who terrorizes Northern California with a killing spree.");

        movieService.insert(movie);
        movieService.insertTranslation(movie);

        List<Movie> userList = movieService.findAll();
        Movie actual = userList.get(userList.size()-1);
        movieService.delete(movie);

        Assert.assertEquals(movie.toString(),actual.toString());
    }

}