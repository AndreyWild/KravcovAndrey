package com.senla.model;

import com.senla.model.enums.OrderStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order extends AEntity{

    private Guest guest;
    private Room room;
    private List<Maintenance> maintenances = new ArrayList<>();
    private LocalDate checkIn;
    private LocalDate checkOut;
    private OrderStatus status;

    public Order() {
    }

    public Order(Guest guest, Room room) {
        this.guest = guest;
        this.room = room;
    }

    public Order(Guest guest, Room room, List<Maintenance> maintenances) {
        this.guest = guest;
        this.room = room;
        this.maintenances = maintenances;
    }

    public Order(Order order) {
        setId(order.getId());
        this.guest = order.getGuest();
        this.room = order.getRoom();
        this.checkIn = order.getCheckIn();
        this.checkOut = order.getCheckOut();
        this.maintenances = order.getMaintenances();
        this.status = order.getStatus();
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", guest=" + guest +
                ", room=" + room +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", maintenances=" + maintenances +
                ", status=" + status +
                '}';
    }
}
