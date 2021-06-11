package com.senla.ui.actions.guest;

import com.senla.util.ScannerInit;
import com.senla.util.sorter.maintenance.MaintNameComparator;

import java.util.Scanner;

public class GuestMaintenancesSortedByNameAction extends AGuestAction{
    @Override
    public void execute() {
        Scanner scanner = ScannerInit.getINSTANCE();
        System.out.print("Enter guest id: ");
        Long guestId = scanner.nextLong();
        guestService.getAllMaintenancesGuest(guestId, new MaintNameComparator()).forEach(System.out::println);
    }
}
