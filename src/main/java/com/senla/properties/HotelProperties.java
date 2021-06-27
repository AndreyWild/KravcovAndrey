package com.senla.properties;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class HotelProperties implements IHotelProperties {

    private static final Logger LOGGER = Logger.getLogger(HotelProperties.class.getName());

    private static final File FILE = new File("src/main/java/com/senla/properties/hotel.properties");
    private static Properties properties = new Properties();

    private static Boolean accessToRoomStatus;
    private static Integer countRoomHistory;

    static {
        try (FileInputStream fileInputStream = new FileInputStream(FILE)) {
            properties.load(fileInputStream);
            accessToRoomStatus = Boolean.parseBoolean(properties.getProperty("room.change.status", "true"));
            countRoomHistory = Integer.parseInt(properties.getProperty("room.history", "1"));
        } catch (FileNotFoundException e) {
            LOGGER.warn("File not found!", e);
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.warn(e);
            e.printStackTrace();
        }
    }

    @Override
    public Boolean getRoomStatus() {
        return accessToRoomStatus;
    }

    @Override
    public Integer getCountRoomHistory() {
        return countRoomHistory;
    }
}
