package com.senla.ui.actions.maintenance;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;
import com.senla.util.GlobalScanner;
import com.senla.util.exceptions.EntityNotFoundException;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class MaintenanceGetByIdAction extends AAction {

    private static final Logger LOGGER = Logger.getLogger(MaintenanceGetByIdAction.class.getName());

    @Override
    public void execute() {

        if (CheckingListForEmptiness.maintenanceListEmpty()) {
            return;
        }
        Scanner scanner = GlobalScanner.getInstance();
        System.out.print("Enter id maintenance: ");
        Long maintenanceId = scanner.nextLong();
        try {
            System.out.println(hotelFacade.getMaintenanceById(maintenanceId));
        } catch (EntityNotFoundException ex) {
            LOGGER.warn(ex.getMessage(), ex);
            System.err.println(ex.getMessage());
        }
    }
}
