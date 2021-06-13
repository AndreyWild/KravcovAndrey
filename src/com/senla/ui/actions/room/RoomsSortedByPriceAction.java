package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;
import com.senla.util.sorter.rooms.RoomsPriceComparator;

public class RoomsSortedByPriceAction extends AAction {
    @Override
    public void execute() {
        hotelFacade.getAllRooms(new RoomsPriceComparator())
                .forEach(System.out::println);
    }
}
