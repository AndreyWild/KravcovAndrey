package com.senla.ui.actions.maintenance;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;
import com.senla.util.GlobalScanner;
import com.senla.util.exceptions.EntityNotFoundException;

import java.util.Scanner;

public class MaintenanceGetByIdAction extends AAction {
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
        } catch (EntityNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
