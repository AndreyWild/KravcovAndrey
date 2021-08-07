package com.senla.properties.connection;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConnectionProperties implements IConnectionProperties{

    private static final Logger LOGGER = Logger.getLogger(ConnectionProperties.class.getName());


    private final String PATH = ClassLoader.getSystemClassLoader().getResource("connect.properties").getPath();
    private final File FILE = new File(PATH);
    private final Properties PROPERTIES = new Properties();

    private String url;
    private String login;
    private String password;
    private String driver;

    public ConnectionProperties() {
        init();
    }

    private void init(){
        try (FileInputStream fileInputStream = new FileInputStream(FILE)) {
            PROPERTIES.load(fileInputStream);
            url = PROPERTIES.getProperty("connect.url");
            login = PROPERTIES.getProperty("connect.login");
            password = PROPERTIES.getProperty("connect.password");
            driver = PROPERTIES.getProperty("connect.driver");
        } catch (FileNotFoundException e) {
            LOGGER.warn("File not found!", e);
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.warn(e);
            e.printStackTrace();
        }
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getDriver() {
        return driver;
    }
}

