package com.senla.model;

import com.senla.api.service.IPriceService;
import com.senla.model.enums.RoomStars;
import com.senla.model.enums.RoomStatus;

import java.util.ArrayList;
import java.util.List;

public class Room extends AEntity implements IPriceService {

    private Integer number;
    private Integer capacity;
    private RoomStatus status = RoomStatus.OPEN;
    private Double price;
    private RoomStars stars = RoomStars.ONE;
    private List<Order> orders = new ArrayList<>();

    public Room() {
    }

    public Room(Room room) {
        setId(room.getId());
        this.number = room.getNumber();
        this.capacity = room.getCapacity();
        this.status = room.getStatus();
        this.price = room.getPrice();
        this.stars = room.getStars();
        this.orders = room.getOrders();
    }

    public Room(Integer number, Integer capacity, Double price, RoomStars stars) {
        this.number = number;
        this.capacity = capacity;
        this.price = price;
        this.stars = stars;
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

    //    @Override
    public Double getPrice() {
        return price;
    }

    //    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    public RoomStars getStars() {
        return stars;
    }

    public void setStars(RoomStars stars) {
        this.stars = stars;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    private Guest getGuest() {
        return orders.stream().reduce((first, second) -> second).get().getGuest();
    }

    @Override
    public String toString() {
        return
                (getId() == null ? "" : getId() + ". ") +
                        "Room-" + number +
                        ", capacity(" + capacity +
                        "), status(" + status +
                        "), price-" + price +
                        "$, stars(" + stars;
    }
}
