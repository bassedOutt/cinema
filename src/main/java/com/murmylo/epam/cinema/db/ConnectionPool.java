package com.murmylo.epam.cinema.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class ConnectionPool {
    private static DataSource dataSource;

    public ConnectionPool(DataSource dataSource) {
        ConnectionPool.dataSource = dataSource;
    }

    private final static Logger LOG = Logger.getLogger(ConnectionPool.class);

    public static synchronized Connection getConnection() {
        if (dataSource == null) {
            try {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                dataSource = (DataSource) envContext.lookup("jdbc/cinema");
            } catch (NamingException e) {
                LOG.error("Cannot find the data source");
            }
        }

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error("Cannot establish connection");
            return null;
        }
    }

}
