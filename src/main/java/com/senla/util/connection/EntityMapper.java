package com.senla.util.connection;

import com.senla.model.*;
import com.senla.model.enums.OrderStatus;
import com.senla.model.enums.RoomStars;
import com.senla.model.enums.RoomStatus;
import com.senla.util.exceptions.EntityParsingException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntityMapper {

    public static AEntity parseResultSet(ResultSet resultSet, String tableName) {
        try {
            switch (tableName) {
                case "hotel.guest":
                    return createGuest(resultSet);
                case "hotel.room":
                    return createRoom(resultSet);
                case "hotel.maintenance":
                    return createMaintenance(resultSet);
                case "hotel.order":
                    return createOrder(resultSet);
                default:
                    throw new RuntimeException("Unknown table: " + tableName);
            }
        } catch (SQLException e) {
            throw new EntityParsingException(e);
        }
    }

    public static Guest createGuest(ResultSet resultSet) throws SQLException {
        Guest guest = new Guest();
        guest.setId(resultSet.getLong("id"));
        guest.setName(resultSet.getString("name"));
        guest.setAge(resultSet.getInt("age"));
        return guest;
    }

    public static Room createRoom(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setId(resultSet.getLong("id"));
        room.setNumber(resultSet.getInt("number"));
        room.setCapacity(resultSet.getInt("capacity"));
        room.setStatus(RoomStatus.valueOf(resultSet.getString("room_status")));
        room.setPrice(resultSet.getDouble("price"));
        room.setStars(RoomStars.valueOf(resultSet.getString("stars")));
        return room;
    }

    public static Maintenance createMaintenance(ResultSet resultSet) throws SQLException {
        Maintenance maintenance = new Maintenance();
        maintenance.setId(resultSet.getLong("id"));
        maintenance.setName(resultSet.getString("name"));
        maintenance.setPrice(resultSet.getDouble("price"));
        return maintenance;
    }

    public static Order createOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong("id"));
        order.setCheckIn(LocalDate.parse(resultSet.getString("check_in")));
        order.setCheckOut(LocalDate.parse(resultSet.getString("check_out")));
        order.setStatus(OrderStatus.valueOf(resultSet.getString("status")));
        return order;
    }

    public static Order createEagerOrder(ResultSet resultSet) throws SQLException {
        Order order = null;
        Guest guest = new Guest();
        Room room = new Room();
        Maintenance maintenance = new Maintenance();
        List<Maintenance> maintenances = new ArrayList<>();

        while (resultSet.next()) {
            order = createOrder(resultSet);
            guest = createGuest(resultSet);
            room = createRoom(resultSet);

            maintenance.setId(resultSet.getLong("id_maintenance"));
            maintenance.setName(resultSet.getString("m_name"));
            maintenance.setPrice(resultSet.getDouble("m_price"));
            maintenances.add(maintenance);
        }
        guest.setOrder(order);
        room.getOrders().add(order);
        order.setGuest(guest);
        order.setRoom(room);
        order.setMaintenances(maintenances);

        return order;
    }
}
