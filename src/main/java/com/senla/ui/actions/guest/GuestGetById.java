package com.senla.ui.actions.guest;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;
import com.senla.util.GlobalScanner;
import com.senla.util.exceptions.EntityNotFoundException;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class GuestGetById extends AAction {

    private static final Logger LOGGER = Logger.getLogger(GuestGetById.class.getName());

    @Override
    public void execute() {
        if (CheckingListForEmptiness.guestsListEmpty()) {
            return;
        }
        Scanner scanner = GlobalScanner.getInstance();
        System.out.print("Enter Id guest: ");
        Long guestId = scanner.nextLong();
        try {
            System.out.println(hotelFacade.getGuestById(guestId));
        } catch (EntityNotFoundException ex) {
            LOGGER.warn(ex.getMessage(), ex);
            System.err.println(ex.getMessage());
        }
    }
}
