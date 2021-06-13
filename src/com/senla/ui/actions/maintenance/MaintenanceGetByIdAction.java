package com.senla.ui.actions.maintenance;

import com.senla.ui.actions.AAction;

import java.util.Scanner;

public class MaintenanceGetByIdAction extends AAction {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter id maintenance: ");
        Long maintenanceId = scanner.nextLong();
        System.out.println(hotelFacade.getMaintenanceById(maintenanceId));
    }
}
