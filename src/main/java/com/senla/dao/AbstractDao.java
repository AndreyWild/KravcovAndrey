package com.senla.dao;

import com.senla.api.dao.IGenericDao;
import com.senla.model.AEntity;
import com.senla.util.connection.Connector;
import com.senla.util.connection.EntityMapper;
import com.senla.util.connection.ListEntityMapper;
import com.senla.util.exceptions.DaoException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public abstract class AbstractDao<T extends AEntity> implements IGenericDao<T> {

    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class.getName());

    protected final Connector connector = Connector.getInstance();

    @Override
    public T save(T entity) {
        Connection connection = connector.getConnection();
        String sql = getInsertQuery();

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForCreate(statement, entity);
            int affected = statement.executeUpdate();
            if (affected == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                entity.setId(resultSet.getLong(1));
            } else {
                throw new DaoException("Creation filed!");
            }
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T getById(Long id) {
        Connection connection = connector.getConnection();
        //String sql = "SELECT * FROM hotel.guest WHERE id =?";
        String sql = String.format("SELECT * FROM %s WHERE id =?", getTableName());

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return (T) EntityMapper.parseResultSet(resultSet, getTableName());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<T> getAll() {
        Connection connection = connector.getConnection();
        String sql = String.format("SELECT * FROM %s;", getTableName());

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            return (List<T>) ListEntityMapper.parseResultSetToList(resultSet, getTableName());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(T entity) {
        Connection connection = connector.getConnection();
        String sql = String.format("DELETE * FROM %s WHERE id =?", getTableName());

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public T update(T entity) {
        Connection connection = connector.getConnection();
        String sql = getUpdateQuery();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForUpdate(statement, entity);
            statement.executeUpdate();
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract String getInsertQuery();

    protected abstract void prepareStatementForCreate(PreparedStatement statement, T entity) throws SQLException;

    protected abstract String getTableName();

    protected abstract String getUpdateQuery();

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T entity) throws SQLException;
}
