package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;

public class NumberOfAvailableRoomsAction extends AAction {
    @Override
    public void execute() {
        if(CheckingListForEmptiness.roomsListEmpty()){
            return;
        }
        System.out.println(hotelFacade.getNumberOfAvailableRooms() + " free rooms");
    }
}
