package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;
import com.senla.util.sorter.rooms.RoomsPriceComparator;

public class OpenRoomsSortedByPriceAction extends AAction {
    @Override
    public void execute() {
        hotelFacade.getOpenRooms(new RoomsPriceComparator())
                .forEach(System.out::println);
    }
}
