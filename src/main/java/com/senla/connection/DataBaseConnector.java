package com.senla.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector extends ConfigDB {

    private static final Logger LOGGER = Logger.getLogger(DataBaseConnector.class.getName());

    private Connection connect;

    private void init() {
        String URL = "jdbc:" + dbType + "://" + dbHost + ":" + dbPort + "/" + dbName;
        try {
            Class.forName(dbDriver);
            connect = DriverManager.getConnection(URL, dbUser, dbPass);
        } catch (ClassNotFoundException e) {
            LOGGER.warn(e.getMessage(), e);
            System.err.println("Class Driver not found!");
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage(), throwables);
            System.err.println("SQL error!");
        }
    }

    private DataBaseConnector() {
    }

    public static DataBaseConnector instance;

    public static DataBaseConnector getInstance() {
        if (instance == null) {
            instance = new DataBaseConnector();
            instance.init();
        }
        return instance;
    }

    public Connection getConnection() {
        return connect;
    }

    public void closeConnection() {
        try {
            connect.close();
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage(), throwables);
            System.err.println(throwables.getMessage());
        }
    }
}
