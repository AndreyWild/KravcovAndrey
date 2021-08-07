package com.senla.facade;

import com.senla.api.service.IPriceService;
import com.senla.model.*;
import com.senla.properties.hotel.HotelProperties;
import com.senla.properties.hotel.IHotelProperties;
import com.senla.service.GuestService;
import com.senla.service.MaintenanceService;
import com.senla.service.MultipleEntitiesService;
import com.senla.service.RoomService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class HotelFacade {

    private GuestService guestService;
    private RoomService roomService;
    private MaintenanceService maintenanceService;
    private MultipleEntitiesService multipleEntitiesService;
    private IHotelProperties properties;

    private void init() {
        guestService = GuestService.getInstance();
        roomService = RoomService.getInstance();
        maintenanceService = MaintenanceService.getInstance();
        multipleEntitiesService = MultipleEntitiesService.getInstance();
        properties = new HotelProperties();
    }

    private HotelFacade() {
    }

    public static HotelFacade instance;

    public static HotelFacade getInstance() {
        if (instance == null) {
            instance = new HotelFacade();
            instance.init();
        }
        return instance;
    }

    //-------------------- GuestService methods --------------------
    public Guest addGuest(String name, Integer age) {
        return guestService.addGuest(name, age);
    }

    public List<Guest> getAllGuests() {
        return guestService.getAll();
    }

    public List<Guest> getAllGuests(Comparator<Guest> comp) {
        return guestService.getAll(comp);
    }

    public List<Guest> getGuestsSortedByDateOut(Comparator<Guest> comp) {
        return guestService.getGuestsSortedByDateOut(comp);
    }

    public Double getInvoiceForRoomAndMaintenances(Long guestId) {
        return guestService.getInvoiceForRoomAndMaintenances(guestId);
    }

    public void orderMaintenance(Long guestId, Long maintenanceId) {
        guestService.orderMaintenance(guestId, maintenanceId);
    }

    public List<Maintenance> getAllMaintenancesGuest(Long guestId) {
        return guestService.getAllMaintenancesGuest(guestId);
    }

    public List<Maintenance> getAllMaintenancesGuest(Long guestId, Comparator<Maintenance> comp) {
        return guestService.getAllMaintenancesGuest(guestId, comp);
    }

    public Guest getGuestById(Long guestId) {
        return guestService.getGuestById(guestId);
    }

    public List<Guest> getTotalNumberOfGuests() {
        return guestService.getTotalNumberOfGuests();
    }

    public void saveGuestsToFile(){
        guestService.saveToFile();
    }

    //-------------------- RoomService methods --------------------

    public Room addRoom(Integer number, Integer capacity, Double price, Integer numberOfStars) {
        return roomService.addRoom(number, capacity, price, numberOfStars);
    }

    public void checkIn(Long guestId, Long roomId, LocalDate dateOut) {
        roomService.checkIn(guestId, roomId, dateOut);
    }

    public void evictGuest(Long guestId) {
        roomService.evictGuest(guestId);
    }

    public List<Room> getAllRooms() {
        return roomService.getAll();
    }

    public List<Room> getAllRooms(Comparator<Room> comp) {
        return roomService.getAll(comp);
    }

    public List<Room> getOpenRooms(Comparator<Room> comp) {
        return roomService.getOpenRooms(comp);
    }

    public Integer getNumberOfAvailableRooms() {
        return roomService.getNumberOfAvailableRooms();
    }

    public List<Room> getAvailableRoomsForDate(LocalDate localDate) {
        return roomService.getAvailableRoomsForDate(localDate);
    }

    public void showThreeLastGuests(Long roomId) {
        roomService.showThreeLastGuests(roomId);
    }

    public Room getRoomById(Long roomId) {
        return roomService.getRoomById(roomId);
    }

    public void changeNumberStatus(Long roomId, RoomStatus status) {
        if (!properties.getRoomStatus()) {
            System.err.println("Access denied!");
            return;
        }
        roomService.changeNumberStatus(roomId, status);
    }

    public void showLastGuests(Long roomId){
        Integer quantity = properties.getCountRoomHistory();
        roomService.showLastGuests(roomId, quantity);
    }

    public void saveRoomsToFile(){
        roomService.saveToFile();
    }

    //-------------------- MaintenanceService methods --------------------
    public Maintenance addMaintenance(String name, Double price) {
        return maintenanceService.addMaintenance(name, price);
    }

    public List<Maintenance> getAllMaintenances() {
        return maintenanceService.getAll();
    }

    public Maintenance getMaintenanceById(Long maintenanceId) {
        return maintenanceService.getMaintenanceById(maintenanceId);
    }

    public List<Maintenance> getAllMaintenances(Comparator<Maintenance> comp) {
        return maintenanceService.getAll(comp);
    }

    public void saveMaintenancesToFile(){
        maintenanceService.saveToFile();
    }

    //-------------------- MultipleEntitiesService methods --------------------
    public List<AEntity> getPricesForMaintenancesAndRoom() {
        return multipleEntitiesService.getPricesForMaintenancesAndRoom();
    }

    public List<IPriceService> getPricesForMaintenancesAndRoom(Comparator<IPriceService> comp) {
        return multipleEntitiesService.getPricesForMaintenancesAndRoom(comp);
    }
}
