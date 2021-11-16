package com.murmylo.epam.cinema.db;

public class Query {
    public static final String INSERT_USER = "INSERT INTO user (name, surname, email, password, is_admin) VALUES (?,?,?,?,default);";
    public static final String GET_USER = "select id,name,surname,email,password,is_admin from user where email=? and password = ?;";
    public static final String GET_ALL_USER = "select id,name,surname,email,password,is_admin from user;";
    public static final String DELETE_USER = "delete from user where email = ? and password = ?";
    public static final String UPDATE_USER = "update user " +
            "set name =?,surname =?, email = ?, password =? where id =?";


    public static final String GET_ALL_MOVIE = "select movie.id,title,language,duration,image_url,start_date,description,price from movie\n" +
            " join movie_translation mt on movie.id = mt.movie_id;";
    public static final String GET_MOVIE = "select movie.id,title,language,duration,image_url,start_date,description,price from movie" +
            " join movie_translation mt on movie.id = mt.movie_id where title = ?";
    public static final String INSERT_MOVIE = "insert into movie (duration, image_url, price, start_date) VALUES (?,?,?,?);";
    public static  final  String INSERT_MOVIE_TRANSLATION = "insert into movie_translation (movie_id, language, title, description) VALUES (?,?,?,?)";
    public static final String DELETE_MOVIE = "delete from movie where id in (select movie_id from movie_translation where title = ?);";

    public static final String UPDATE_MOVIE = "update movie join movie_translation m on movie.id = m.movie_id" +
            " set title =?,language=?,duration=?,image_url=?,start_date=?,description=?,price=?" +
            " where movie.id = ?;";
}
