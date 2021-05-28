package com.senla.api.service;

import com.senla.model.AEntity;
import com.senla.model.Room;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public interface IRoomService {

    Room addRoom(Integer number, Integer capacity, Double price, Integer numberOfStars);

    void checkIn(Long guestId, Long roomId, String dateOut);

    void evictGuest(Long guestId);

    List<Room> getAllRooms();

    List<Room> getAllRooms(Comparator<Room> comp);

    List<Room> getAllOpenRooms(Comparator<Room> comp);

    Integer getNumberOfAvailableRooms();

    List<Room> getAllAvailableNumbersForDate(LocalDate localDate);

    void showThreeLastGuestsRooms(Long roomId);

    void showRoomDetails(Long roomId);
}
