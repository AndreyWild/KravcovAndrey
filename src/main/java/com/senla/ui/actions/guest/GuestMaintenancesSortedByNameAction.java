package com.senla.ui.actions.guest;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;
import com.senla.util.GlobalScanner;
import com.senla.util.exceptions.ServiceEntityNotFoundException;
import com.senla.util.sorter.maintenance.MaintenanceNameComparator;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class GuestMaintenancesSortedByNameAction extends AAction {

    private static final Logger LOGGER = Logger.getLogger(GuestMaintenancesSortedByNameAction.class.getName());

    @Override
    public void execute() {
        try {
            Scanner scanner = GlobalScanner.getInstance();
            System.out.print("Enter guest id: ");
            Long guestId = scanner.nextLong();
            if (CheckingListForEmptiness.guestMaintenancesListEmpty(guestId)) {
                return;
            }
            hotelFacade.getAllMaintenancesGuest(guestId, new MaintenanceNameComparator()).forEach(System.out::println);
        } catch (ServiceEntityNotFoundException ex) {
            LOGGER.warn(ex.getMessage(), ex);
            System.err.println(ex.getMessage());
        }
    }
}
