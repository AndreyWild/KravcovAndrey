package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;

public class NumberOfAvailableRoomsAction extends AAction {
    @Override
    public void execute() {
        System.out.println(hotelFacade.getNumberOfAvailableRooms() + " free rooms");
    }
}
