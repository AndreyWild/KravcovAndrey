package com.senla.service;

import com.senla.api.dao.IRoomDao;
import com.senla.api.service.IRoomService;
import com.senla.model.*;
import com.senla.util.DatePeriodGenerator;
import com.senla.util.InitializerDAO;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RoomService implements IRoomService {

    private static final Logger LOGGER = Logger.getLogger(RoomService.class.getName());

    private final IRoomDao roomDao = InitializerDAO.ROOM_DAO;
    private final GuestService guestService = GuestService.getInstance();

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
        LOGGER.info(String.format("Launch checkIn(%s, %s)", guestId, roomId));
        Room room = roomDao.getById(roomId);
        Guest guest = guestService.getGuestById(guestId);
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
    }

    @Override
    public void evictGuest(Long guestId) {
        LOGGER.info(String.format("Launch evictGuest(%s)", guestId));
        Guest guest = guestService.getGuestById(guestId);
//        Room room = guest.getRoom();
        Room room = roomDao.getById(guest.getRoom().getId());
        room.getGuests().remove(guest);
        guest.setGuestStatus(GuestStatus.NOT_CHECKED);
        room.setStatus(RoomStatus.OPEN);
//        guest.setOut(LocalDate.now());
        guest.setRoom(null);
        guestService.update(guest);
        roomDao.update(room);

        // TODO: 27.05.2021 можно дописать изменение списка занятых дат, получив дату заселения и за дату
        //  выселения  взяв дату вызова метода.
    }

    @Override
    public List<Room> getAll() {
        return roomDao.getAll();
    }

    @Override
    public List<Room> getAll(Comparator<Room> comp) {
        return roomDao.getAll().stream().sorted(comp).collect(Collectors.toList());
    }

    @Override
    public List<Room> getOpenRooms(Comparator<Room> comp) {
        return roomDao.getAll().stream()
                .filter(room -> room.getStatus().equals(RoomStatus.OPEN))
                .sorted(comp)
                .collect(Collectors.toList());
    }

    @Override
    public Integer getNumberOfAvailableRooms() {
        return (int) roomDao.getAll()
                .stream()
                .filter(room -> room.getStatus().equals(RoomStatus.OPEN))
                .count();
    }

    @Override
    public List<Room> getAvailableRoomsForDate(LocalDate localDate) {
        return roomDao.getAll()
                .stream()
                .filter(room -> room.getBusyDates() != null)
                .filter(room -> !room.getBusyDates().contains(localDate))
                .collect(Collectors.toList());
    }

    @Override
    public void showThreeLastGuests(Long roomId) {
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
    }

    @Override
    public Room getRoomById(Long roomId) {
        LOGGER.info(String.format("Launch getRoomById(%s)", roomId));
        return roomDao.getById(roomId);
    }
}

