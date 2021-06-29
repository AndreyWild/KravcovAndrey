package com.senla.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Guest extends AEntity {

    private String name;
    private int age;
    private Room room;
    private List<Maintenance> maintenances;

    private LocalDate in;
    private LocalDate out;

    private GuestStatus guestStatus = GuestStatus.NOT_CHECKED;

    public Guest() {
    }

    public Guest(Guest guest) {
        setId(guest.getId());
        this.name = guest.getName();
        this.age = guest.getAge();
        this.room = guest.getRoom();
        this.maintenances = guest.getMaintenances();
        this.in = guest.getIn();
        this.out = guest.getOut();
        this.guestStatus = guest.getGuestStatus();
    }

    public Guest(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Maintenance> getMaintenances() {
        if (maintenances == null) {
            maintenances = new ArrayList<>();
        }
        return maintenances;
    }

    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }

    public LocalDate getIn() {
        return in;
    }

    public void setIn(LocalDate in) {
        this.in = in;
    }

    public LocalDate getOut() {
        return out;
    }

    public void setOut(LocalDate out) {
        this.out = out;
    }

    public GuestStatus getGuestStatus() {
        return guestStatus;
    }

    public void setGuestStatus(GuestStatus guestStatus) {
        this.guestStatus = guestStatus;
    }

    @Override
    public String toString() {
        return "\t" + getId()
                + ". " + name
                + ", " + age + " age"
                + (room == null ? "" : (", room(" + room.getNumber()) + ")");
    }
}
