package com.senla.ui.actions.guest;

import com.senla.util.ScannerInit;

import java.util.Scanner;

public class BillForRoomAndMaintenancesByIdAction extends AGuestAction{
    @Override
    public void execute() {
        Scanner scanner = ScannerInit.getINSTANCE();
        System.out.print("Enter the guest ID to receive an invoice for the room and services: ");
        Long guestId = scanner.nextLong();
        if(guestService.getById(guestId).getRoom() == null){
            System.out.println(guestService.getById(guestId).getName() + " not settled!");
        } else {
        System.out.println(guestService.getById(guestId).getName() + " have to pay " +
                guestService.getInvoiceForRoomAndMaintenances(guestId) + "$");}
    }
}
