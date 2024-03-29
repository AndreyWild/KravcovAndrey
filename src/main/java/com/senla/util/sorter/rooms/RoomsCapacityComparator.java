package com.senla.util.sorter.rooms;

import com.senla.model.Room;

import java.util.Comparator;

public class RoomsCapacityComparator implements Comparator<Room> {

    @Override
    public int compare(Room room1, Room room2) {
        return room1.getCapacity() - room2.getCapacity();
    }
}
