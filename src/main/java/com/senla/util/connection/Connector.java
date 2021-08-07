package com.senla.util.connection;

import com.senla.properties.connection.ConnectionProperties;
import com.senla.properties.connection.IConnectionProperties;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static final Logger LOGGER = Logger.getLogger(Connector.class.getName());

    private static final IConnectionProperties CONNECTION_PROPERTIES = new ConnectionProperties();

    private static final String URL = CONNECTION_PROPERTIES.getUrl();
    private static final String LOGIN = CONNECTION_PROPERTIES.getLogin();
    private static final String PASS = CONNECTION_PROPERTIES.getPassword();
    private static final String DRIVER = CONNECTION_PROPERTIES.getDriver();

    private static Connector instance;
    private Connection connection;

    private Connector() {
        init();
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                init();
            }
            return connection;
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static Connector getInstance() {
        if (instance == null) {
            instance = new Connector();
            instance.init();
        }
        return instance;
    }

    private void init() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, PASS);
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}