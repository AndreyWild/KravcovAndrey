package com.senla.dao;

import com.senla.api.dao.IRoomDao;
import com.senla.model.Room;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomDao extends AbstractDao<Room> implements IRoomDao {

    // INSERT INTO hotel.room (number, capacity, price, stars) VALUES (?, ?, ?, ?);
    private static final String INSERT_QUERY = "INSERT INTO hotel.room (number, capacity, price, stars) VALUES (?, ?, ?, ?);";
//            "INSERT INTO "
//                    + RoomConst.TABLE + " ("
//                    + RoomConst.NUMBER + ", "
//                    + RoomConst.CAPACITY + ", "
//                    + RoomConst.PRICE + ", "
//                    + RoomConst.STARS + ") VALUES (?, ?, ?, ?);";

    // UPDATE hotel.room SET id=?, number=?, capacity=?, room_status=?, price=?, stars=? WHERE id=?;
    private static final String UPDATE_QUERY = "UPDATE hotel.room SET id=?, number=?, capacity=?, room_status=?, price=?, stars=? WHERE id=?;";
//            "UPDATE "
//            + RoomConst.TABLE + " SET "
//            + RoomConst.ID + "=?, "
//            + RoomConst.NUMBER + "=?, "
//            + RoomConst.CAPACITY + "=?, "
//            + RoomConst.STATUS + "=?, "
//            + RoomConst.PRICE + "=?, "
//            + RoomConst.STARS + "=?  WHERE "
//            + RoomConst.ID + "=?;";

    @Override
    protected String getInsertQuery() {
        return INSERT_QUERY;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Room entity) throws SQLException {
        statement.setInt(1, entity.getNumber());
        statement.setInt(2, entity.getCapacity());
        statement.setDouble(3, entity.getPrice());
        statement.setString(4, String.valueOf(entity.getStars()));
    }

    @Override
    protected String getTableName() {
        return ROOM_TABLE;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Room entity) throws SQLException {
        statement.setLong(1, entity.getId());
        statement.setInt(2, entity.getNumber());
        statement.setInt(3, entity.getCapacity());
        statement.setString(4, String.valueOf(entity.getStatus()));
        statement.setDouble(5, entity.getPrice());
        statement.setString(6, String.valueOf(entity.getStars()));
        statement.setLong(7, entity.getId());
    }
}
