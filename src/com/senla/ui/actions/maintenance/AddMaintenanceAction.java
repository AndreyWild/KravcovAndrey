package com.senla.ui.actions.maintenance;

import com.senla.ui.actions.AAction;

import java.util.Scanner;

public class AddMaintenanceAction extends AAction {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the service: ");
        String name = scanner.nextLine();
        System.out.print("Enter the name of the service: ");
        Double price = scanner.nextDouble();
        hotelFacade.addMaintenance(name, price);
        System.out.println("Service " + name + " with a " + price + " of 200 created!");
    }
}
