package com.senla.dao;

import com.senla.connection.DataBaseConnector;
import com.senla.model.AEntity;

import java.sql.Connection;

public abstract class AbstractDao<T extends AEntity> {

    DataBaseConnector connector = DataBaseConnector.getInstance();
    Connection connect = connector.getConnection();
}
