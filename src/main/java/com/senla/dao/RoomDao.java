package com.senla.dao;

import com.senla.api.dao.IRoomDao;
import com.senla.connection.constants.RoomConst;
import com.senla.model.Room;
import com.senla.model.enums.RoomStars;
import com.senla.model.enums.RoomStatus;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDao extends AbstractDao<Room> implements IRoomDao {

    private static final Logger LOGGER = Logger.getLogger(RoomDao.class.getName());

    @Override
    public void save(Room entity) {

        try (PreparedStatement preparedStatement = connect.prepareStatement(
                "INSERT INTO hotel."
                        + RoomConst.TABLE + "("
                        + RoomConst.NUMBER + ","
                        + RoomConst.CAPACITY + ","
                        + RoomConst.PRICE + ","
                        + RoomConst.STARS
                        + ") VALUES (?, ?, ?, ?)")) {

            preparedStatement.setInt(1, entity.getNumber());
            preparedStatement.setInt(2, entity.getCapacity());
            preparedStatement.setDouble(3, entity.getPrice());
            preparedStatement.setString(4, String.valueOf(entity.getStars()));

            preparedStatement.executeUpdate();
            System.out.println("The room " + entity + " has been created!");
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage(), throwables);
            System.err.println(throwables.getMessage());
        }
    }

    @Override
    public Room getById(Long id) {

        Room room = null;

        try (PreparedStatement preparedStatement = connect.prepareStatement(
                "SELECT * FROM hotel."
                        + RoomConst.TABLE + " WHERE "
                        + RoomConst.ID + "=?")) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            room = new Room();
            room.setId(resultSet.getLong(RoomConst.ID));
            room.setNumber(resultSet.getInt(RoomConst.NUMBER));
            room.setCapacity(resultSet.getInt(RoomConst.CAPACITY));
            room.setStatus(RoomStatus.valueOf(resultSet.getString(RoomConst.STATUS)));
            room.setPrice(resultSet.getDouble(RoomConst.PRICE));
            room.setStars(RoomStars.valueOf(resultSet.getString(RoomConst.STARS)));
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage(), throwables);
            System.err.println(throwables.getMessage());
        }
        return room;
    }

    @Override
    public List<Room> getAll() {
        List<Room> rooms = new ArrayList<>();

        try (PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM hotel." + RoomConst.TABLE)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Room room = new Room();

                room.setId(resultSet.getLong(RoomConst.ID));
                room.setNumber(resultSet.getInt(RoomConst.NUMBER));
                room.setCapacity(resultSet.getInt(RoomConst.CAPACITY));
                room.setStatus(RoomStatus.valueOf(resultSet.getString(RoomConst.STATUS)));
                room.setPrice(resultSet.getDouble(RoomConst.PRICE));
                room.setStars(RoomStars.valueOf(resultSet.getString(RoomConst.STARS)));

                rooms.add(room);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rooms;
    }

    @Override
    public void delete(Room entity) {

        try (PreparedStatement preparedStatement = connect.prepareStatement(
                "DELETE FROM hotel." + RoomConst.TABLE + " WHERE " + RoomConst.ID + "=?")) {

            preparedStatement.setLong(1, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage(), throwables);
            System.err.println(throwables.getMessage());
        }
    }

    @Override
    public void update(Room entity) {

        try (PreparedStatement preparedStatement = connect.prepareStatement(
                "UPDATE hotel."
                        + RoomConst.TABLE + " SET "
                        + RoomConst.ID + "=?, "
                        + RoomConst.NUMBER + "=?, "
                        + RoomConst.CAPACITY + "=?, "
                        + RoomConst.STATUS + "=?, "
                        + RoomConst.PRICE + "=?, "
                        + RoomConst.STARS + "=?  WHERE "
                        + RoomConst.ID + "=?")) {

            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setInt(2, entity.getNumber());
            preparedStatement.setInt(3, entity.getCapacity());
            preparedStatement.setString(4, String.valueOf(entity.getStatus()));
            preparedStatement.setDouble(5, entity.getPrice());
            preparedStatement.setString(6, String.valueOf(entity.getStars()));
            preparedStatement.setLong(7, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage(), throwables);
            System.err.println(throwables.getMessage());
        }
    }
}