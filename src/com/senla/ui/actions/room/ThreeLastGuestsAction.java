package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;
import com.senla.util.GlobalScanner;
import com.senla.util.exceptions.EntityNotFoundException;

import java.util.Scanner;

public class ThreeLastGuestsAction extends AAction {
    @Override
    public void execute() {
        try {
            Scanner scanner = GlobalScanner.getInstance();
            System.out.print("Enter id room: ");
            Long roomId = scanner.nextLong();
            hotelFacade.showThreeLastGuests(roomId);
        } catch (EntityNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
