package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;
import com.senla.util.GlobalScanner;
import com.senla.util.exceptions.EntityNotFoundException;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class EvictGuestFromRoomAction extends AAction {

    private static final Logger LOGGER = Logger.getLogger(EvictGuestFromRoomAction.class.getName());

    @Override
    public void execute() {

        Scanner scanner = GlobalScanner.getInstance();
        System.out.print("Enter Id guest: ");
        Long guestId = scanner.nextLong();
        try {
            hotelFacade.evictGuest(guestId);
            System.out.println("Guest has been evicted!");
        } catch (EntityNotFoundException ex) {
            LOGGER.warn(ex.getMessage(), ex);
            System.err.println(ex.getMessage());
        }
    }
}
