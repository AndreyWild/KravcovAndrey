package com.senla.ui.actions.guest;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;
import com.senla.util.sorter.maintenance.MaintenanceNameComparator;

import java.util.Scanner;

public class GuestMaintenancesSortedByNameAction extends AAction {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter guest id: ");
        Long guestId = scanner.nextLong();
        if (CheckingListForEmptiness.guestMaintenancesListEmpty(guestId)) {
            return;
        }
        hotelFacade.getAllMaintenancesGuest(guestId, new MaintenanceNameComparator()).forEach(System.out::println);
    }
}
