package com.senla.ui.actions.guest;

import com.senla.ui.actions.AAction;

import java.util.Scanner;

public class GetBillAction extends AAction {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the guest ID to receive an invoice for the room and services: ");
        Long guestId = scanner.nextLong();
        if (hotelFacade.getGuestById(guestId).getRoom() == null) {
            System.out.println(hotelFacade.getGuestById(guestId).getName() + " not settled!");
        } else {
            System.out.println(hotelFacade.getGuestById(guestId).getName() + " have to pay " +
                    hotelFacade.getInvoiceForRoomAndMaintenances(guestId) + "$");
        }
    }
}
