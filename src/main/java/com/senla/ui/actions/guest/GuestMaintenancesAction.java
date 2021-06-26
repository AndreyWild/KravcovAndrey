package com.senla.ui.actions.guest;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;
import com.senla.util.GlobalScanner;
import com.senla.util.exceptions.EntityNotFoundException;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class GuestMaintenancesAction extends AAction {

    private static final Logger LOGGER = Logger.getLogger(GuestMaintenancesAction.class.getName());

    @Override
    public void execute() {
        Scanner scanner = GlobalScanner.getInstance();
        System.out.print("Enter guest id: ");
        Long guestId = scanner.nextLong();
        if (CheckingListForEmptiness.guestMaintenancesListEmpty(guestId)) {
            return;
        }
        try {
            hotelFacade.getAllMaintenancesGuest(guestId).forEach(System.out::println);
        } catch (EntityNotFoundException ex) {
            LOGGER.warn(ex.getMessage(), ex);
            System.err.println(ex.getMessage());
        }
    }
}
