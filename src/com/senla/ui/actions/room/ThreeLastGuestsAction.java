package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;

import java.util.Scanner;

public class ThreeLastGuestsAction extends AAction {
    @Override
    public void execute() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter id room: ");
            Long roomId = scanner.nextLong();
            hotelFacade.showThreeLastGuests(roomId);
        } catch (NullPointerException ex) {
            System.err.println("No guest with this index!");
        }
    }
}
