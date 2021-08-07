package com.senla.dao;

import com.senla.api.dao.IOrderDao;
import com.senla.model.Guest;
import com.senla.model.Maintenance;
import com.senla.model.Order;
import com.senla.model.Room;
import com.senla.model.enums.OrderStatus;
import com.senla.model.enums.RoomStars;
import com.senla.model.enums.RoomStatus;
import com.senla.util.connection.EntityMapper;
import com.senla.util.connection.constants.*;
import com.senla.util.exceptions.DaoException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends AbstractDao<Order> implements IOrderDao {

    // INSERT INTO hotel.order (id_guest, id_room, checkIn, checkOut) value(?, ?, ?, ?);
    private static final String INSERT_QUERY =
            "INSERT INTO "
                    + OrderConst.TABLE + " ("
                    + OrderConst.ID_GUEST + ", "
                    + OrderConst.ID_ROOM + ", "
                    + OrderConst.CHECK_IN + ", "
                    + OrderConst.CHECK_OUT + ") value(?, ?, ?, ?);";

    // UPDATE hotel.order SET id=?, id_guest=?, id_room=?, check_in=?, check_out=?, status=? WHERE id=?;
    private static final String UPDATE_QUERY = "UPDATE "
            + OrderConst.TABLE + " SET "
            + OrderConst.ID + "=?, "
            + OrderConst.ID_GUEST + "=?, "
            + OrderConst.ID_ROOM + "=?, "
            + OrderConst.CHECK_IN + "=?, "
            + OrderConst.CHECK_OUT + "=?, "
            + OrderConst.STATUS + "=?  WHERE "
            + OrderConst.ID + "=?;";

    @Override
    public Order update(Order entity) {
        return null;
    }

    @Override
    protected String getInsertQuery() {
        return INSERT_QUERY;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Order entity) throws SQLException {
        statement.setLong(1, entity.getGuest().getId());
        statement.setLong(2, entity.getRoom().getId());
        statement.setString(3, String.valueOf(entity.getCheckIn()));
        statement.setString(4, String.valueOf(entity.getCheckOut()));
    }

    @Override
    protected String getTableName() {
        return OrderConst.TABLE;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Order entity) throws SQLException {
        statement.setLong(1, entity.getId());
        statement.setLong(2, entity.getGuest().getId());
        statement.setLong(3, entity.getRoom().getId());
        statement.setDate(4, Date.valueOf(entity.getCheckIn()));
        statement.setDate(5, Date.valueOf(entity.getCheckOut()));
        statement.setString(6, String.valueOf(entity.getStatus()));
        statement.setLong(7, entity.getId());
    }

    public Order getEagerById(Long id) {
        Connection connection = connector.getConnection();
        Order order = null;

//    SELECT o.id, o.id_guest, g.name, g.age, o.id_room, r.number, r.capacity, r.roomStatus, r.price, r.stars,
//    o.checkIn, o.checkOut, o.status, om.id_maintenance, m.name, m.price
//        FROM hotel.order o
//        NATURAL JOIN hotel.guest g
//        NATURAL JOIN hotel.room r
//        Left outer JOIN hotel.ord_maint om ON om.id_order = o.id
//        Left outer JOIN hotel.maintenance m ON m.id = om.id_maintenance
//        where o.id = ?

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT o.id, o.id_guest, " +
                        "g.name, g.age, o.id_room, " +
                        "r.number, r.capacity, r.room_status, r.price, r.stars, " +
                        "o.check_in, o.check_out, o.status, " +
                        "om.id_maintenance, " +
                        "m.name 'm_name' , m.price 'm_price'" +
                        " FROM " + OrderConst.TABLE + " o" +
                        " NATURAL JOIN " + GuestConst.TABLE + " g" +
                        " NATURAL JOIN " + RoomConst.TABLE + " r" +
                        " Left outer JOIN " + OrdMaintConst.TABLE + " om ON om.id_order = o.id" +
                        " Left outer JOIN " + MaintenanceConst.TABLE + " m ON m.id = om.id_maintenance" +
                        " where o.id = ?")) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            order = EntityMapper.createEagerOrder(resultSet);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return order;
    }
}
