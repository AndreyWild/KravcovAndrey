package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;

public class RoomsAction extends AAction {
    @Override
    public void execute() {
        if(CheckingListForEmptiness.roomsListEmpty()){
            return;
        }
        hotelFacade.getAllRooms().forEach(System.out::println);
    }
}
