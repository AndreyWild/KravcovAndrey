package com.senla.dao;

import com.senla.api.dao.IOrderDao;
import com.senla.connection.constants.OrderConst;
import com.senla.model.Order;
import com.senla.model.enums.OrderStatus;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends AbstractDao<Order> implements IOrderDao {

    private static final Logger LOGGER = Logger.getLogger(OrderDao.class.getName());

    @Override
    public void save(Order entity) {

        try (PreparedStatement preparedStatement = connect.prepareStatement(
                "INSERT INTO hotel."
                        + OrderConst.TABLE + "("
                        + OrderConst.ID_GUEST + ","
                        + OrderConst.ID_ROOM + ","
                        + OrderConst.CHECK_IN + ","
                        + OrderConst.CHECK_OUT
                        + ") VALUES (?, ?, ?, ?)")) {

            preparedStatement.setLong(1, entity.getGuest().getId());
            preparedStatement.setLong(2, entity.getRoom().getId());
            preparedStatement.setString(3, String.valueOf(entity.getCheckIn()));
            preparedStatement.setString(4, String.valueOf(entity.getCheckOut()));

            preparedStatement.executeUpdate();
            System.out.println("The order " + entity + " has been created!");
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            LOGGER.warn(throwables.getMessage(), throwables);
            System.err.println(throwables.getMessage());
        }
    }

    @Override
    public Order getById(Long id) {

        Order order = null;

        try (PreparedStatement preparedStatement = connect.prepareStatement(
                "SELECT * FROM hotel."
                        + OrderConst.TABLE + " WHERE "
                        + OrderConst.ID + "=?")) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            order = new Order();

            order.setId(resultSet.getLong(OrderConst.ID));
            order.setCheckIn(LocalDate.parse(resultSet.getString(OrderConst.CHECK_IN)));
            order.setCheckOut(LocalDate.parse(resultSet.getString(OrderConst.CHECK_OUT)));
            order.setStatus(OrderStatus.valueOf(resultSet.getString(OrderConst.STATUS)));

        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage(), throwables);
            System.err.println(throwables.getMessage());
        }
        return order;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();

        try (PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM hotel." + OrderConst.TABLE)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();

                order.setId(resultSet.getLong(OrderConst.ID));
                order.setCheckIn(LocalDate.parse(resultSet.getString(OrderConst.CHECK_IN)));
                order.setCheckOut(LocalDate.parse(resultSet.getString(OrderConst.CHECK_OUT)));
                order.setStatus(OrderStatus.valueOf(resultSet.getString(OrderConst.STATUS)));

                orders.add(order);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return orders;
    }

    @Override
    public void delete(Order entity) {

        try (PreparedStatement preparedStatement = connect.prepareStatement(
                "DELETE FROM hotel." + OrderConst.TABLE + " WHERE " + OrderConst.ID + "=?")) {

            preparedStatement.setLong(1, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage(), throwables);
            System.err.println(throwables.getMessage());
        }
    }

    @Override
    public void update(Order entity) {

        try (PreparedStatement preparedStatement = connect.prepareStatement(
                "UPDATE hotel."
                        + OrderConst.TABLE + " SET "
                        + OrderConst.ID + "=?, "
                        + OrderConst.ID_GUEST + "=?, "
                        + OrderConst.ID_ROOM + "=?, "
                        + OrderConst.CHECK_IN + "=?, "
                        + OrderConst.CHECK_OUT + "=?, "
                        + OrderConst.STATUS + "=?  WHERE "
                        + OrderConst.ID + "=?")) {

            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setLong(2, entity.getGuest().getId());
            preparedStatement.setLong(3, entity.getRoom().getId());
            preparedStatement.setDate(4, Date.valueOf(entity.getCheckIn()));
            preparedStatement.setDate(5, Date.valueOf(entity.getCheckOut()));
            preparedStatement.setString(6, String.valueOf(entity.getStatus()));
            preparedStatement.setLong(7, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.warn(throwables.getMessage(), throwables);
            System.err.println(throwables.getMessage());
        }
    }
}
