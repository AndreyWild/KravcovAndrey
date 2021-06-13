package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;

public class RoomsAction extends AAction {
    @Override
    public void execute() {
        hotelFacade.getAllRooms().forEach(System.out::println);
    }
}
