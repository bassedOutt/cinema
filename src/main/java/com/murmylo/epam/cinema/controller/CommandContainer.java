package com.murmylo.epam.cinema.controller;

import com.murmylo.epam.cinema.controller.login.LoginCommand;
import com.murmylo.epam.cinema.controller.login.LogoutCommand;
import com.murmylo.epam.cinema.controller.login.SignupCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {

    private static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("logout", new SignupCommand());
    }

    public static Command get(String commandName) {
        return commands.get(commandName);
    }
}
