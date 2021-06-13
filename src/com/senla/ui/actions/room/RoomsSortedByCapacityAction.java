package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;
import com.senla.util.sorter.rooms.RoomsCapacityComparator;

public class RoomsSortedByCapacityAction extends AAction {
    @Override
    public void execute() {
        hotelFacade.getAllRooms(new RoomsCapacityComparator())
                .forEach(System.out::println);
    }
}
