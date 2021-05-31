package com.senla.util.sorter.rooms;

import com.senla.model.Room;

import java.util.Comparator;

public class RoomsPriceComparator implements Comparator<Room> {

    @Override
    public int compare(Room room1, Room room2) {
        return (int) (room1.getPrice() - room2.getPrice());
    }
}
