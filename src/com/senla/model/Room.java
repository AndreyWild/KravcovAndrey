package com.senla.model;

import com.senla.api.service.IPriceService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Room extends AEntity implements IPriceService {

    private Integer number;
    private Integer capacity;
    private RoomStatus status = RoomStatus.OPEN;
    private Double price;
    private Integer numberOfStars;
    private List<Guest> guests;
    private List<Guest> guestHistory;
    private List<LocalDate> busyDates;

    //private LocalDate freeRoomDate = null;
    //private LocalDate freeRoomDate = LocalDate.of(2000, 01, 01);
    //private List<Maintenance> roomMaintenances;

    public Room(Integer number, Integer capacity, Double price, Integer numberOfStars) {
        this.number = number;
        this.capacity = capacity;
        this.price = price;
        this.numberOfStars = numberOfStars;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(Integer numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public List<Guest> getGuests() {
        if (guests == null) {
            guests = new ArrayList<>();
        }
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public List<LocalDate> getBusyDates() {
        if (busyDates == null) {
            busyDates = new ArrayList<>();
        }
        return busyDates;
    }

    public void setBusyDates(List<LocalDate> busyDates) {
        this.busyDates = busyDates;
    }

    public List<Guest> getGuestHistory() {
        if (guestHistory == null) {
            guestHistory = new ArrayList<>();
        }
        return guestHistory;
    }

    public void setGuestHistory(List<Guest> guestHistory) {
        this.guestHistory = guestHistory;
    }

    @Override
    public String toString() {
        return "Room number(" + number + ")"
                + " id-" + getId()
                + ", capacity(" + capacity
                + "), status(" + status
                + "), price(" + price
                + "), stars(" + numberOfStars
                + ") guests:\n" + (guests == null ? "empty" : guests);
    }
}
