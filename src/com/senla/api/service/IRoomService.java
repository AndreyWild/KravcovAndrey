package com.senla.api.service;

import com.senla.model.Room;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public interface IRoomService {

    Room addRoom(Integer number, Integer capacity, Double price, Integer numberOfStars);

    void checkIn(Long guestId, Long roomId, LocalDate dateOut);

    void evictGuest(Long guestId);

    List<Room> getAllRooms();

    List<Room> getAllRooms(Comparator<Room> comp);

    List<Room> getOpenRooms(Comparator<Room> comp);

    Integer getNumberOfAvailableRooms();

    List<Room> getAvailableRoomsForDate(LocalDate localDate);

    void showThreeLastGuests(Long roomId);

    Room getRoomById(Long roomId);
}
