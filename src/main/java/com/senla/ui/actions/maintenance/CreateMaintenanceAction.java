package com.senla.ui.actions.maintenance;

import com.senla.ui.actions.AAction;
import com.senla.util.GlobalScanner;

import java.util.Scanner;

public class CreateMaintenanceAction extends AAction {

    @Override
    public void execute() {
        Scanner scanner = GlobalScanner.getInstance();
        System.out.print("Enter the name of the service: ");
        String name = scanner.nextLine();
        System.out.print("Enter the price of the service: ");
        Double price = scanner.nextDouble();
        if (price < 0) {
            System.err.println("The cost cannot be negative!");
            return;
        }
        hotelFacade.addMaintenance(name, price);
        System.out.println("Service " + name + " with a " + price + " of 200 created!");
    }
}
