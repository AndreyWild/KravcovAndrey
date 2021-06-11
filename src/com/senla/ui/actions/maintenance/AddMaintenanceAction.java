package com.senla.ui.actions.maintenance;

import com.senla.util.ScannerInit;

import java.util.Scanner;

public class AddMaintenanceAction extends AMaintenanceAction {
    @Override
    public void execute() {
        Scanner scanner = ScannerInit.getINSTANCE();
        System.out.print("Enter the name of the service: ");
        String name = scanner.nextLine();
        System.out.print("Enter the name of the service: ");
        Double price = scanner.nextDouble();
        maintenanceService.addMaintenance(name, price);
        System.out.println("Service " + name + " with a " + price + " of 200 created!");
    }
}
