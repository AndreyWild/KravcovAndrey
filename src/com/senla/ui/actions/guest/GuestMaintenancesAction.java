package com.senla.ui.actions.guest;

import com.senla.util.ScannerInit;

import java.util.Scanner;

public class GuestMaintenancesAction extends AGuestAction {
    @Override
    public void execute() {
        Scanner scanner = ScannerInit.getINSTANCE();
        System.out.print("Enter guest id: ");
        Long guestId = scanner.nextLong();
        guestService.getAllMaintenancesGuest(guestId).forEach(System.out::println);
    }
}
