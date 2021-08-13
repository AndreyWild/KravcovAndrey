package com.senla.dao;

import com.senla.api.dao.IMaintenanceDao;
import com.senla.model.Maintenance;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MaintenanceDao extends AbstractDao<Maintenance> implements IMaintenanceDao {

    private static final String INSERT_QUERY = "INSERT INTO hotel.maintenance (name, price) VALUES (?, ?);";

    private static final String UPDATE_QUERY = "UPDATE hotel.maintenance SET id=?, name=?, price=? WHERE id=?;";

    @Override
    protected String getInsertQuery() {
        return INSERT_QUERY;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Maintenance entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setDouble(2, entity.getPrice());
    }

    @Override
    protected String getTableName() {
        return MAINT_TABLE;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Maintenance entity) throws SQLException {
        statement.setLong(1, entity.getId());
        statement.setString(2, entity.getName());
        statement.setDouble(3, entity.getPrice());
        statement.setLong(4, entity.getId());
    }

    @Override
    public Maintenance update(Maintenance entity) {
        Maintenance maintenance = super.getById(entity.getId());
        maintenance.setName(entity.getName());
        maintenance.setPrice(entity.getPrice());
        return maintenance;
    }
}
