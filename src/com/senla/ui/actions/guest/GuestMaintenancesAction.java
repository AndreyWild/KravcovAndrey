package com.senla.ui.actions.guest;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;

import java.util.Scanner;

public class GuestMaintenancesAction extends AAction {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter guest id: ");
        Long guestId = scanner.nextLong();
        if (CheckingListForEmptiness.guestMaintenancesListEmpty(guestId)) {
            return;
        }
        hotelFacade.getAllMaintenancesGuest(guestId).forEach(System.out::println);
    }
}
