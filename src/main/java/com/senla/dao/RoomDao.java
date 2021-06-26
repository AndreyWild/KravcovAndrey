package com.senla.dao;

import com.senla.api.dao.IRoomDao;
import com.senla.model.Room;

public class RoomDao extends AbstractDao<Room> implements IRoomDao {

    @Override
    public Room update(Room entity) {
        Room room = getById(entity.getId());
        room.setNumber(entity.getNumber());
        room.setCapacity(entity.getCapacity());
        room.setStatus(entity.getStatus());
        room.setPrice(entity.getPrice());
        room.setNumberOfStars(entity.getNumberOfStars());
        room.setGuests(entity.getGuests());
        return room;
    }
}
