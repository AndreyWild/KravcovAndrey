package com.senla.service;

import com.senla.api.dao.IGuestDao;
import com.senla.api.dao.IMaintenanceDao;
import com.senla.api.dao.IRoomDao;
import com.senla.api.service.IRoomService;
import com.senla.model.*;
import com.senla.util.DatePeriodGenerator;
import com.senla.util.InitializerDAO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RoomService implements IRoomService {

    private final IGuestDao guestDao = InitializerDAO.GUEST_DAO;
    private final IRoomDao roomDao = InitializerDAO.ROOM_DAO;
    private final IMaintenanceDao maintenanceDao = InitializerDAO.MAINTENANCE_DAO;

//    public RoomService(IRoomDao roomDao, IGuestDao guestDao, IMaintenanceDao maintenanceDao) {
//        this.roomDao = roomDao;
//        this.guestDao = guestDao;
//        this.maintenanceDao = maintenanceDao;
//    }

    private RoomService() {
    }
    private static RoomService INSTANCE;


    public static RoomService getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new RoomService();
        }
        return INSTANCE;
    }

    @Override
    public Room addRoom(Integer number, Integer capacity, Double price, Integer numberOfStars) {
        Room room = new Room(number, capacity, price, numberOfStars);
        roomDao.save(room);
        return room;
    }

    @Override
    public void checkIn(Long guestId, Long roomId, String dateOut) { // добавление гостя в номер
        Room room = roomDao.getById(roomId);

        //if(room.getStatus() == RoomStatus.OPEN){
        Guest guest = guestDao.getById(guestId);
        room.getGuests().add(guest);
        guest.setRoom(room);
        room.setStatus(RoomStatus.CLOSED);
        guest.setGuestStatus(GuestStatus.CHECKED);
        LocalDate inDate = LocalDate.now();
        LocalDate outDate = LocalDate.parse(dateOut, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        room.getGuestHistory().add(guest);
        guest.setIn(inDate);
        guest.setOut(outDate);
        room.getBusyDates().addAll(DatePeriodGenerator.toDateList(inDate, outDate));
        //}
    }

    @Override
    public void evictGuest(Long guestId) {
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
    public void showRoomDetails(Long roomId) {
        System.out.println(roomDao.getById(roomId));
    }

    @Override
    public Room getById(Long roomId) {
        return roomDao.getById(roomId);
    }
}

