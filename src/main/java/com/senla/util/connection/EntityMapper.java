package com.senla.util.connection;

import com.senla.model.*;
import com.senla.model.enums.OrderStatus;
import com.senla.model.enums.RoomStars;
import com.senla.model.enums.RoomStatus;
import com.senla.util.connection.constants.GuestConst;
import com.senla.util.connection.constants.MaintenanceConst;
import com.senla.util.connection.constants.OrderConst;
import com.senla.util.connection.constants.RoomConst;
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
                case GuestConst.TABLE:
                    return createGuest(resultSet);
                case RoomConst.TABLE:
                    return createRoom(resultSet);
                case MaintenanceConst.TABLE:
                    return createMaintenance(resultSet);
                case OrderConst.TABLE:
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
        guest.setId(resultSet.getLong(GuestConst.ID));
        guest.setName(resultSet.getString(GuestConst.NAME));
        guest.setAge(resultSet.getInt(GuestConst.AGE));
        return guest;
    }

    public static Room createRoom(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setId(resultSet.getLong(RoomConst.ID));
        room.setNumber(resultSet.getInt(RoomConst.NUMBER));
        room.setCapacity(resultSet.getInt(RoomConst.CAPACITY));
        room.setStatus(RoomStatus.valueOf(resultSet.getString(RoomConst.STATUS)));
        room.setPrice(resultSet.getDouble(RoomConst.PRICE));
        room.setStars(RoomStars.valueOf(resultSet.getString(RoomConst.STARS)));
        return room;
    }

    public static Maintenance createMaintenance(ResultSet resultSet) throws SQLException {
        Maintenance maintenance = new Maintenance();
        maintenance.setId(resultSet.getLong(MaintenanceConst.ID));
        maintenance.setName(resultSet.getString(MaintenanceConst.NAME));
        maintenance.setPrice(resultSet.getDouble(MaintenanceConst.PRICE));
        return maintenance;
    }

    public static Order createOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong(OrderConst.ID));
        order.setCheckIn(LocalDate.parse(resultSet.getString(OrderConst.CHECK_IN)));
        order.setCheckOut(LocalDate.parse(resultSet.getString(OrderConst.CHECK_OUT)));
        order.setStatus(OrderStatus.valueOf(resultSet.getString(OrderConst.STATUS)));
        return order;
    }

}
