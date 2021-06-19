package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;
import com.senla.util.GlobalScanner;
import com.senla.util.exceptions.EntityNotFoundException;

import java.util.Scanner;

public class RoomGetByIdAction extends AAction {
    @Override
    public void execute() {
        try {
            if (CheckingListForEmptiness.roomsListEmpty()) {
                return;
            }
            Scanner scanner = GlobalScanner.getInstance();
            System.out.print("Enter id room: ");
            Long roomId = scanner.nextLong();
            System.out.println(hotelFacade.getRoomById(roomId));
        } catch (EntityNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
