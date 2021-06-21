package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;
import com.senla.util.GlobalScanner;
import com.senla.util.exceptions.EntityNotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Formatter;
import java.util.Scanner;

public class AddGuestInRoomAction extends AAction {
    @Override
    public void execute() {
        try {
            if (CheckingListForEmptiness.guestsListEmpty() | CheckingListForEmptiness.roomsListEmpty()) {
                return;
            }
            Scanner scanner = GlobalScanner.getInstance();
            System.out.print("Enter the guest ID: ");
            Long guestId = scanner.nextLong();
            System.out.print("Enter the room ID: ");
            Long roomId = scanner.nextLong();
            System.out.print("Enter the check-out date in the format (ddMMyyyy): ");
            String outDate = scanner.next();
            LocalDate dateOut = LocalDate.parse(outDate, DateTimeFormatter.ofPattern("ddMMyyyy"));
            hotelFacade.checkIn(guestId, roomId, dateOut);
            System.out.println("The guest is checked into the room!");
        } catch (DateTimeParseException ex) {
            System.err.println("Date entered incorrectly!");

        } catch (
                EntityNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

