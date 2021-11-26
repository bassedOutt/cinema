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

    public static final String INSERT_SESSION = "insert into session(movie_id, start_time,seats_num, end_time, date, pricing_id) values (?,?,default,?,?,?)";
    public static final String CREATE_SEATS = "call insertSeats(?,?)";
    public static final String GET_SESSION = "select s.id, s.movie_id, start_time, end_time, seats_num, date, pricing_id, m.id, duration, image_url, m.price, start_date, mt.id, language, title, description, p.id, name, p.price from session s\n" +
            "    join movie m on s.movie_id = m.id\n" +
            "    join movie_translation mt on m.id = mt.movie_id\n" +
            "    left join pricing p on s.pricing_id = p.id\n" +
            "    where language = ? and s.id = ?";
    public static final String GET_ALL_SESSION = "select s.id, s.movie_id, start_time, end_time, seats_num, date, pricing_id, m.id, duration, image_url, m.price, start_date, mt.id, language, title, description, p.id, name, p.price from session s\n" +
            "    join movie m on s.movie_id = m.id\n" +
            "    join movie_translation mt on m.id = mt.movie_id\n" +
            "    left join pricing p on s.pricing_id = p.id;";
    public static final String GET_SESSION_SEATS = "select id,nrow,number,is_vip,receipt_id,is_taken,session_id,movie_id from seat where session_id = ?;";
    public static final String DELETE_SESSION = "delete from session where id = ?";
    public static final String UPDATE_SESSION = "update session set" +
            " start_time = ?,end_time = ?, seats_num = ?,date = ?, pricing_id=? where id = ?";

    public static final String GET_SEAT = "select id,nrow,number,is_vip,is_taken,session_id,movie_id from seat where id = ?";
    public static final String GET_ALL_SEAT = "select id,nrow,number,is_vip,is_taken,session_id,movie_id from seat";
    public static final String DELETE_SEAT = "delete from seat where id = ?";
    public static final String UPDATE_SEAT = "update seat set" +
            " is_taken = ? where id = ?";

    public static final String GET_TICKET = "select id,user_id,price,seat_id,seat_session_id,seat_movie_id from ticket where id =?";
    public static final String GET_ALL_TICKET = "select id,user_id,price,seat_id,seat_session_id,seat_movie_id from ticket";
    public static final String INSERT_TICKET = "insert into ticket(user_id,price,seat_id,seat_session_id,seat_movie_id) values(?,?,?,?,?)";
    public static final String DELETE_TICKET = "delete from ticket where id = ?";



}
