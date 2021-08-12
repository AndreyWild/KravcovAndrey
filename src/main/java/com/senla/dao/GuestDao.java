package com.senla.dao;

import com.senla.api.dao.IGuestDao;
import com.senla.model.Guest;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GuestDao extends AbstractDao<Guest> implements IGuestDao {

    // INSERT INTO hotel.guest (name, age) VALUES (?, ?);
    private static final String INSERT_QUERY = "INSERT INTO hotel.guest (name, age) VALUES (?, ?);";
//            "INSERT INTO "
//            + GuestConst.TABLE + " ("
//            + GuestConst.NAME + ", "
//            + GuestConst.AGE + ") VALUES (?, ?);";

    // UPDATE hotel.guest SET id=?, name=?, age=? WHERE id=?;
    private static final String UPDATE_QUERY = "UPDATE hotel.guest SET id=?, name=?, age=? WHERE id=?;";
//            "UPDATE "
//            + GuestConst.TABLE + " SET "
//            + GuestConst.ID + "=?, "
//            + GuestConst.NAME + "=?, "
//            + GuestConst.AGE + "=? WHERE "
//            + GuestConst.ID + "=?;";

    @Override
    protected String getInsertQuery() {
        return INSERT_QUERY;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Guest entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setInt(2, entity.getAge());
    }

    @Override
    protected String getTableName() {
        return GUEST_TABLE;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Guest entity) throws SQLException {
        statement.setLong(1, entity.getId());
        statement.setString(2, entity.getName());
        statement.setInt(3, entity.getAge());
        statement.setLong(4, entity.getId());
    }
}
