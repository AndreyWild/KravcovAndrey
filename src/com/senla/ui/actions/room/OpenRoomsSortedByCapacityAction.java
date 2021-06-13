package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;
import com.senla.util.sorter.rooms.RoomsCapacityComparator;

public class OpenRoomsSortedByCapacityAction extends AAction {
    @Override
    public void execute() {
        if(CheckingListForEmptiness.roomsListEmpty()){
            return;
        }
        hotelFacade.getOpenRooms(new RoomsCapacityComparator())
                .forEach(System.out::println);
    }
}
