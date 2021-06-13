package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;
import com.senla.util.sorter.rooms.RoomsStarsComparator;

public class RoomsSortedByStarsAction extends AAction {
    @Override
    public void execute() {
        hotelFacade.getAllRooms(new RoomsStarsComparator())
                .forEach(System.out::println);
    }
}
