package com.senla.ui.actions.guest;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;

import java.util.Scanner;

public class GuestGetById extends AAction {
    @Override
    public void execute() {
        if (CheckingListForEmptiness.guestsListEmpty()) {
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Id guest: ");
        Long guestId = scanner.nextLong();
        System.out.println(hotelFacade.getGuestById(guestId));

    }
}
