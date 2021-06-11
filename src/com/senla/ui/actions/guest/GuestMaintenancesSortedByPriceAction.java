package com.senla.ui.actions.guest;

import com.senla.util.ScannerInit;
import com.senla.util.sorter.maintenance.MaintenancePriceComparator;

import java.util.Scanner;

public class GuestMaintenancesSortedByPriceAction extends AGuestAction{
    @Override
    public void execute() {
        Scanner scanner = ScannerInit.getINSTANCE();
        System.out.print("Enter guest id: ");
        Long guestId = scanner.nextLong();
        guestService.getAllMaintenancesGuest(guestId, new MaintenancePriceComparator()).forEach(System.out::println);
    }
}
