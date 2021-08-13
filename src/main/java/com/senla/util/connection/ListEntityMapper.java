package com.senla.util.connection;

import com.senla.model.*;
import com.senla.util.exceptions.EntityParsingException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListEntityMapper {

    public static List<? extends AEntity> parseResultSetToList(ResultSet resultSet, String tableName) {
        try {
            switch (tableName) {
                case "hotel.guest":
                    return createListGuest(resultSet);
                case "hotel.room":
                    return createListRoom(resultSet);
                case "hotel.maintenance":
                    return createListMaintenance(resultSet);
                case "hotel.order":
                    return createListOrder(resultSet);
                default:
                    throw new RuntimeException("Unknown table: " + tableName);
            }
        } catch (SQLException e) {
            throw new EntityParsingException(e);
        }
    }

    private static List<Guest> createListGuest(ResultSet resultSet) throws SQLException {
        List<Guest> guests = new ArrayList<>();
        while (resultSet.next()) {
            Guest guest = EntityMapper.createGuest(resultSet);
            guests.add(guest);
        }
        return guests;
    }

    private static List<Room> createListRoom(ResultSet resultSet) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        while (resultSet.next()) {
            Room room = EntityMapper.createRoom(resultSet);
            rooms.add(room);
        }
        return rooms;
    }

    private static List<Maintenance> createListMaintenance(ResultSet resultSet) throws SQLException {
        List<Maintenance> maintenances = new ArrayList<>();
        while (resultSet.next()) {
            Maintenance maintenance = EntityMapper.createMaintenance(resultSet);
            maintenances.add(maintenance);
        }
        return maintenances;
    }

    private static List<Order> createListOrder(ResultSet resultSet) throws SQLException {
        List<Order> orders = new ArrayList<>();

        while (resultSet.next()) {
            Order order = EntityMapper.createOrder(resultSet);
            orders.add(order);
        }
        return orders;
    }
}
