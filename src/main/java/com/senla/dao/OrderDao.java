package com.senla.dao;

import com.senla.api.dao.IOrderDao;
import com.senla.model.Order;
import com.senla.util.connection.EntityMapper;
import com.senla.util.exceptions.DaoException;

import java.sql.*;

public class OrderDao extends AbstractDao<Order> implements IOrderDao {

    private static final String INSERT_QUERY = "INSERT INTO hotel.order (id_guest, id_room, checkIn, checkOut) value(?, ?, ?, ?);";

    private static final String UPDATE_QUERY = "UPDATE hotel.order SET id=?, id_guest=?, id_room=?, check_in=?, check_out=?, status=? WHERE id=?;";

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
        return ORDER_TABLE;
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

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT o.id, o.id_guest, g.name, g.age, o.id_room, " +
                        "r.number, r.capacity, r.roomStatus, r.price, r.stars, " +
                        "o.checkIn, o.checkOut, o.status, om.id_maintenance, m.name, m.price " +
                        "FROM hotel.order o " +
                        "NATURAL JOIN hotel.guest g " +
                        "NATURAL JOIN hotel.room r " +
                        "LEFT OUTER JOIN hotel.ord_maint om ON om.id_order = o.id " +
                        "LEFT OUTER JOIN hotel.maintenance m ON m.id = om.id_maintenance " +
                        "WHERE o.id = ?;")) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            order = EntityMapper.createEagerOrder(resultSet);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return order;
    }
}
