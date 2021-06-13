package com.senla.ui.actions.guest;

import com.senla.ui.actions.AAction;

import java.util.Scanner;

public class GuestMaintenancesAction extends AAction {
    @Override
    public void execute() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter guest id: ");
            Long guestId = scanner.nextLong();
            if (hotelFacade.getAllMaintenancesGuest(guestId).isEmpty()) {
                System.out.println("-=The guest did not order services!=-");
            } else {
                hotelFacade.getAllMaintenancesGuest(guestId).forEach(System.out::println);
            }
        } catch (NullPointerException ex) {
            System.err.println("No guest with this index!");
        }
    }
}
