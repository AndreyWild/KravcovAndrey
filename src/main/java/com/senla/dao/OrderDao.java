package com.senla.dao;

import com.senla.api.dao.IOrderDao;
import com.senla.model.Order;
import com.senla.util.connection.constants.OrderConst;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
