package com.senla.dao;

import com.senla.api.dao.IOrderDao;
import com.senla.model.Order;

public class OrderDao extends AbstractDao<Order> implements IOrderDao {

    @Override
    public Order update(Order entity) {
        return null;
    }
}
