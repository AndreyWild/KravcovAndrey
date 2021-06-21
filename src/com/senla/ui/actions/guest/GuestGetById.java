package com.senla.ui.actions.guest;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;
import com.senla.util.GlobalScanner;
import com.senla.util.exceptions.EntityNotFoundException;

import java.util.Scanner;

public class GuestGetById extends AAction {
    @Override
    public void execute() {
        try {
            if (CheckingListForEmptiness.guestsListEmpty()) {
                return;
            }
            Scanner scanner = GlobalScanner.getInstance();
            System.out.print("Enter Id guest: ");
            Long guestId = scanner.nextLong();
            System.out.println(hotelFacade.getGuestById(guestId));
        } catch (EntityNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
