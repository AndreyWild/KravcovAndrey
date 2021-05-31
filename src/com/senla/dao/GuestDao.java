package com.senla.dao;

import com.senla.api.dao.IGuestDao;
import com.senla.model.Guest;


public class GuestDao extends AbstractDao<Guest> implements IGuestDao { // Implementing the IGuestDao interface

    /* Most of the methods are implemented in AbstractDao */

    @Override
    public Guest update(Guest entity) {
        Guest guest = getById(entity.getId());
        guest.setName(entity.getName());
        guest.setAge(entity.getAge());

        return guest;
    }
}