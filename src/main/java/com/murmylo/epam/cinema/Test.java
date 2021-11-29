package com.murmylo.epam.cinema;
import java.sql.Time;

public class Test {

    public static void main(String[] args) {
        Time time = Time.valueOf("13:00:00");
        Time time2 = Time.valueOf("13:00:00");

        System.out.println(time.after(time));
        System.out.println(time.before(time2));
    }
}

