package com.senla.api.service;

import com.senla.model.Room;
import com.senla.model.RoomStatus;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public interface IRoomService {

    Room addRoom(Integer number, Integer capacity, Double price, Integer numberOfStars);

    void checkIn(Long guestId, Long roomId, LocalDate dateOut);

    void evictGuest(Long guestId);

    List<Room> getAll();

    List<Room> getAll(Comparator<Room> comp);

    List<Room> getOpenRooms(Comparator<Room> comp);

    Integer getNumberOfAvailableRooms();

    List<Room> getAvailableRoomsForDate(LocalDate localDate);

    void showThreeLastGuests(Long roomId);

    Room getRoomById(Long roomId);

    void changeNumberStatus(Long roomId, RoomStatus status);

    void showLastGuests(Long roomId, Integer quantity);

    void setList(List<Room> rooms);

    void saveToFile();

    Room update(Room entity);
}
