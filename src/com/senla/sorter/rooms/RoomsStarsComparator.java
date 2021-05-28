package com.senla.sorter.rooms;

import com.senla.model.Room;

import java.util.Comparator;

public class RoomsStarsComparator implements Comparator<Room> {

    @Override
    public int compare(Room room1, Room room2) {
        return room1.getNumberOfStars() - room2.getNumberOfStars();
    }
}
