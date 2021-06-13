package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;

import java.util.Scanner;

public class EvictGuestFromRoomAction extends AAction {
    @Override
    public void execute() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Id guest: ");
            Long guestId = scanner.nextLong();
            hotelFacade.evictGuest(guestId);
            System.out.println("Guest has been evicted!");
        } catch (NullPointerException ex) {
            System.err.println("No guest with this index!");
        }
    }
}
