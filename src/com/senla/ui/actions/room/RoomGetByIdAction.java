package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;

import java.util.Scanner;

public class RoomGetByIdAction extends AAction {
    @Override
    public void execute() {
        if (CheckingListForEmptiness.roomsListEmpty()) {
            return;
        }
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter id room: ");
            Long roomId = scanner.nextLong();
            System.out.println(hotelFacade.getRoomById(roomId));
        } catch (NullPointerException ex) {
            System.err.println("No room with this index!");
        }
    }
}
