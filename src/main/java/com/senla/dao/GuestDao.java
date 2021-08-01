package com.senla.dao;

import com.senla.api.dao.IGuestDao;
import com.senla.connection.constants.GuestConst;
import com.senla.model.Guest;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestDao extends AbstractDao<Guest> implements IGuestDao {

    private static final Logger LOGGER = Logger.getLogger(GuestDao.class.getName());

    @Override
    public void save(Guest entity) {

        try (PreparedStatement preparedStatement = connect.prepareStatement(
                "INSERT INTO hotel."
                        + GuestConst.TABLE + "("
                        + GuestConst.NAME + ","
                        + GuestConst.AGE
                        + ") VALUES (?, ?)")) {

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getAge());

            preparedStatement.executeUpdate();
            System.out.println("The guest " + entity + " has been created!");
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage(), throwables);
            System.err.println(throwables.getMessage());
        }
    }

    @Override
    public Guest getById(Long id) {

        Guest guest = null;

        try (PreparedStatement preparedStatement = connect.prepareStatement(
                "SELECT * FROM hotel." + GuestConst.TABLE + " WHERE " + GuestConst.ID + "=?")) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            guest = new Guest();
            guest.setId(resultSet.getLong(GuestConst.ID));
            guest.setName(resultSet.getString(GuestConst.NAME));
            guest.setAge(resultSet.getInt(GuestConst.AGE));
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage(), throwables);
            System.err.println(throwables.getMessage());
        }
        return guest;
    }

    @Override
    public List<Guest> getAll() {
        List<Guest> guests = new ArrayList<>();

        try (PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM hotel." + GuestConst.TABLE)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Guest guest = new Guest();

                guest.setId(resultSet.getLong(GuestConst.ID));
                guest.setName(resultSet.getString(GuestConst.NAME));
                guest.setAge(resultSet.getInt(GuestConst.AGE));

                guests.add(guest);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return guests;
    }

    @Override
    public void delete(Guest entity) {

        try (PreparedStatement preparedStatement = connect.prepareStatement(
                "DELETE FROM hotel."
                        + GuestConst.TABLE + " WHERE "
                        + GuestConst.ID + "=?")) {

            preparedStatement.setLong(1, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage(), throwables);
            System.err.println(throwables.getMessage());
        }
    }

    @Override
    public void update(Guest entity) {

        try (PreparedStatement preparedStatement = connect.prepareStatement(
                "UPDATE hotel."
                        + GuestConst.TABLE + " SET "
                        + GuestConst.ID + "=?, "
                        + GuestConst.NAME + "=?, "
                        + GuestConst.AGE + "=? WHERE "
                        + GuestConst.ID + "=?")) {

            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setInt(3, entity.getAge());
            preparedStatement.setLong(4, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage(), throwables);
            System.err.println(throwables.getMessage());
        }
    }
}

