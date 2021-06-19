package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;
import com.senla.util.GlobalScanner;
import com.senla.util.exceptions.EntityNotFoundException;

import java.util.Scanner;

public class EvictGuestFromRoomAction extends AAction {
    @Override
    public void execute() {
        try {
            Scanner scanner = GlobalScanner.getInstance();
            System.out.print("Enter Id guest: ");
            Long guestId = scanner.nextLong();
            hotelFacade.evictGuest(guestId);
            System.out.println("Guest has been evicted!");
        } catch (EntityNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
