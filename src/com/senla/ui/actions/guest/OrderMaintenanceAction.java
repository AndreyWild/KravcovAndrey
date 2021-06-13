package com.senla.ui.actions.guest;

import com.senla.ui.actions.AAction;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrderMaintenanceAction extends AAction {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter id guest: ");
            Long idGuest = scanner.nextLong();
            System.out.print("Enter id maintenance: ");
            Long idMaintenance = scanner.nextLong();
            hotelFacade.orderMaintenance(idGuest, idMaintenance);
            System.out.println("Maintenance added!");
        } catch (NullPointerException exception) {
            System.out.println("There is no such guest or service!");
        } catch (InputMismatchException exception) {
            System.out.println("You are doing something wrong!");
        }
    }
}
