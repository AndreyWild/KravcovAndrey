package com.senla.dao;

import com.senla.api.dao.IRoomDao;
import com.senla.model.Room;

public class RoomDao extends AbstractDao<Room> implements IRoomDao {

//    private List<Room> rooms = new ArrayList<>();
//
//    @Override
//    public void save(Room entity) {
//        entity.setId(IdGenerator.generateRoomId());
//        rooms.add(entity);
//    }
//
//    @Override
//    public Room getById(Long id) {
//        return rooms.stream() // стрим из массива
//                .filter(x -> id.equals(x.getId())) // фильтрация по id
//                .findFirst() // вернуть первый элемент коллекции
//                .orElse(null); // или 0, если коллекция пуста
//    }
//
//    @Override
//    public List<Room> getAll() {
//        return new ArrayList<>(rooms);
//    }
//
//    @Override
//    public void delete(Room entity) {
//        rooms.remove(entity);
//
//    }

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
