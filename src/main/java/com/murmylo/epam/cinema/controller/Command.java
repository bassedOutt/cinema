package com.murmylo.epam.cinema.controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {

    public abstract void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException;

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
