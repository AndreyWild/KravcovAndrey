package com.senla.dao;

import com.senla.api.dao.IGuestDao;
import com.senla.model.Guest;

public class GuestDao extends AbstractDao<Guest> implements IGuestDao {

    @Override
    public Guest getById(Long id){
        return new Guest(super.getById(id));
    }

    @Override
    public Guest update(Guest entity) {
        Guest guest = super.getById(entity.getId());
        guest.setName(entity.getName());
        guest.setAge(entity.getAge());
        guest.setRoom(entity.getRoom());
        guest.setMaintenances(entity.getMaintenances());
        guest.setIn(entity.getIn());
        guest.setOut(entity.getOut());
        guest.setGuestStatus(entity.getGuestStatus());
        return guest;
    }
}
