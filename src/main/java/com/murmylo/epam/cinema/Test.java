package com.murmylo.epam.cinema;

import com.murmylo.epam.cinema.db.dao.UserDAO;
import com.murmylo.epam.cinema.db.entity.User;

public class Test {
    public static void main(String[] args) {
        String name = "Mark";
        String surname = "Zuckerberg";
        String email = "zuckerberg@facebook.com";
        String password = "zuckpass";

        User user = new User(name,surname,email,password);
        User updatedUser = new User(user);

        updatedUser.setEmail("zuck@gmail.com");

        System.out.println(updatedUser);
    }
}
