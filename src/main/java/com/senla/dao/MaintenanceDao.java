package com.senla.dao;

import com.senla.api.dao.IMaintenanceDao;
import com.senla.connection.constants.MaintenanceConst;
import com.senla.model.Maintenance;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceDao extends AbstractDao<Maintenance> implements IMaintenanceDao {

    private static final Logger LOGGER = Logger.getLogger(MaintenanceDao.class.getName());

    @Override
    public void save(Maintenance entity) {

        try (PreparedStatement stmt = connect.prepareStatement(
                "INSERT INTO hotel."
                        + MaintenanceConst.TABLE + "("
                        + MaintenanceConst.NAME + ","
                        + MaintenanceConst.PRICE
                        + ") VALUES (?, ?)")) {

            stmt.setString(1, entity.getName());
            stmt.setDouble(2, entity.getPrice());

            stmt.executeUpdate();
            System.out.println("The Maintenance " + entity + " has been created!");
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage(), throwables);
            System.err.println(throwables.getMessage());
        }
    }

    @Override
    public Maintenance getById(Long id) {

        Maintenance maintenance = null;

        try (PreparedStatement preparedStatement = connect.prepareStatement(
                "SELECT * FROM hotel." + MaintenanceConst.TABLE + " WHERE " + MaintenanceConst.ID + "=?")) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            maintenance = new Maintenance();
            maintenance.setId(resultSet.getLong(MaintenanceConst.ID));
            maintenance.setName(resultSet.getString(MaintenanceConst.NAME));
            maintenance.setPrice(resultSet.getDouble(MaintenanceConst.PRICE));
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage(), throwables);
            System.err.println(throwables.getMessage());
        }
        return maintenance;
    }

    @Override
    public List<Maintenance> getAll() {
        List<Maintenance> maintenances = new ArrayList<>();

        try (PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM hotel." + MaintenanceConst.TABLE)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Maintenance maintenance = new Maintenance();

                maintenance.setId(resultSet.getLong(MaintenanceConst.ID));
                maintenance.setName(resultSet.getString(MaintenanceConst.NAME));
                maintenance.setPrice(resultSet.getDouble(MaintenanceConst.PRICE));

                maintenances.add(maintenance);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return maintenances;
    }

    @Override
    public void delete(Maintenance entity) {

        try (PreparedStatement preparedStatement = connect.prepareStatement(
                "DELETE FROM hotel." + MaintenanceConst.TABLE + " WHERE " + MaintenanceConst.ID + "=?")) {

            preparedStatement.setLong(1, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage(), throwables);
            System.err.println(throwables.getMessage());
        }
    }

    @Override
    public void update(Maintenance entity) {

        try (PreparedStatement preparedStatement = connect.prepareStatement(
                "UPDATE hotel."
                        + MaintenanceConst.TABLE + " SET "
                        + MaintenanceConst.ID + "=?, "
                        + MaintenanceConst.NAME + "=?, "
                        + MaintenanceConst.PRICE + "=? WHERE "
                        + MaintenanceConst.ID + "=?")) {

            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setDouble(3, entity.getPrice());
            preparedStatement.setLong(4, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage(), throwables);
            System.err.println(throwables.getMessage());
        }
    }
}

