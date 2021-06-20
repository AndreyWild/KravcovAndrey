package com.senla.ui.actions.maintenance;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;
import com.senla.util.GlobalScanner;
import com.senla.util.exceptions.ServiceException;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class MaintenanceGetByIdAction extends AAction {

    private static final Logger LOGGER = Logger.getLogger(MaintenanceGetByIdAction.class.getName());

    @Override
    public void execute() {
        try {
            if (CheckingListForEmptiness.maintenanceListEmpty()) {
                return;
            }
            Scanner scanner = GlobalScanner.getInstance();
            System.out.print("Enter id maintenance: ");
            Long maintenanceId = scanner.nextLong();
            System.out.println(hotelFacade.getMaintenanceById(maintenanceId));
        } catch (ServiceException ex) {
            LOGGER.warn(ex.getMessage(), ex);
            System.err.println(ex.getMessage());
        }
    }
}
