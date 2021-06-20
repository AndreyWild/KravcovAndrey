package com.senla.service;

import com.senla.api.dao.IGuestDao;
import com.senla.api.dao.IRoomDao;
import com.senla.api.service.IRoomService;
import com.senla.model.*;
import com.senla.util.DatePeriodGenerator;
import com.senla.util.InitializerDAO;
import com.senla.util.exceptions.DaoException;
import com.senla.util.exceptions.ServiceException;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RoomService implements IRoomService {

    private static final Logger LOGGER = Logger.getLogger(RoomService.class.getName());

    private final IGuestDao guestDao = InitializerDAO.GUEST_DAO;
    private final IRoomDao roomDao = InitializerDAO.ROOM_DAO;

    private RoomService() {
    }

    private static RoomService instance;

    public static RoomService getInstance() {
        return Objects.requireNonNullElse(instance, new RoomService());
    }

    static {
        RoomService roomService = RoomService.getInstance();
        roomService.addRoom(101, 1, 250.0, 5);
        roomService.addRoom(102, 2, 200.0, 4);
        roomService.addRoom(103, 3, 150.0, 3);
        roomService.addRoom(104, 4, 100.0, 2);
        roomService.addRoom(105, 1, 350.0, 5);
        roomService.addRoom(106, 2, 300.0, 4);
        roomService.addRoom(107, 3, 250.0, 3);
        roomService.addRoom(108, 4, 200.0, 2);
        roomService.addRoom(109, 1, 500.0, 5);
        roomService.addRoom(110, 2, 450.0, 4);
    }

    @Override
    public Room addRoom(Integer number, Integer capacity, Double price, Integer numberOfStars) {
        Room room = new Room(number, capacity, price, numberOfStars);
        roomDao.save(room);
        return room;
    }

    @Override
    public void checkIn(Long guestId, Long roomId, LocalDate dateOut) {
        try {
            LOGGER.info(String.format("Launch checkIn(%s, %s)", guestId, roomId));
            Room room = roomDao.getById(roomId);
            Guest guest = guestDao.getById(guestId);
            room.getGuests().add(guest);
            guest.setRoom(room);
            room.setStatus(RoomStatus.CLOSED);
            guest.setGuestStatus(GuestStatus.CHECKED);
            LocalDate inDate = LocalDate.now();
            LocalDate outDate = dateOut;
            room.getGuestHistory().add(guest);
            guest.setIn(inDate);
            guest.setOut(outDate);
            room.getBusyDates().addAll(DatePeriodGenerator.toDateList(inDate, outDate));
        } catch (DaoException e) {
            LOGGER.warn("checkIn - failed!", e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void evictGuest(Long guestId) {
        try {
            LOGGER.info(String.format("Launch evictGuest(%s)", guestId));
            Guest guest = guestDao.getById(guestId);
//        Room room = guest.getRoom();
            Room room = roomDao.getById(guest.getRoom().getId());
            room.getGuests().remove(guest);
            guest.setGuestStatus(GuestStatus.NOT_CHECKED);
            room.setStatus(RoomStatus.OPEN);
//        guest.setOut(LocalDate.now());
            guest.setRoom(null);
            guestDao.update(guest);
            roomDao.update(room);
        } catch (DaoException e) {
            LOGGER.warn("evictGuest - failed!", e);
            throw new ServiceException(e.getMessage());
        }

        // TODO: 27.05.2021 можно дописать изменение списка занятых дат, получив дату заселения и за дату
        //  выселения  взяв дату вызова метода.
    }

    @Override
    public List<Room> getAll() {
        return roomDao.getAll();
    }

    @Override
    public List<Room> getAll(Comparator<Room> comp) {
        List<Room> rooms = roomDao.getAll();
        rooms.sort(comp);
        return rooms;
    }

    @Override
    public List<Room> getOpenRooms(Comparator<Room> comp) {
        List<Room> rooms = roomDao.getAll().stream()
                .filter(room -> room.getStatus().equals(RoomStatus.OPEN))
                .collect(Collectors.toList());
        rooms.sort(comp);
        return rooms;
    }

    @Override
    public Integer getNumberOfAvailableRooms() {
        List<Room> rooms = roomDao.getAll()
                .stream()
                .filter(room -> room.getStatus().equals(RoomStatus.OPEN))
                .collect(Collectors.toList());
        return rooms.size();
    }

    @Override
    public List<Room> getAvailableRoomsForDate(LocalDate localDate) {
        List<Room> rooms = roomDao.getAll()
                .stream()
                .filter(room -> room.getBusyDates() != null)
                .filter(room -> !room.getBusyDates().contains(localDate))
                .collect(Collectors.toList());
        return rooms;
    }

    @Override
    public void showThreeLastGuests(Long roomId) {
        try {
            LOGGER.info(String.format("Launch showThreeLastGuests(%s)", roomId));
            Room room = roomDao.getById(roomId);
            List<Guest> guests = room.getGuestHistory();
            if (room.getGuestHistory().size() <= 3) {
                for (Guest guest : guests) {
                    System.out.println(guest.getName() + " " + guest.getIn() + "-" + guest.getOut());
                }
            } else {
                for (int i = guests.size() - 3; i < guests.size(); i++) {
                    System.out.println(guests.get(i).getName() + " " + guests.get(i).getIn() + "-" + guests.get(i).getOut());
                }
            }
        } catch (DaoException e) {
            LOGGER.warn("showThreeLastGuests - failed!", e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Room getRoomById(Long roomId) {
        try {
            LOGGER.info(String.format("Launch getRoomById(%s)", roomId));
            return roomDao.getById(roomId);
        } catch (DaoException e) {
            LOGGER.warn("getRoomById - failed!", e);
            throw new ServiceException(e.getMessage());
        }
    }
}

