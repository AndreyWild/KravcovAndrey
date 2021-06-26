package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;
import com.senla.util.GlobalScanner;
import com.senla.util.exceptions.EntityNotFoundException;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Scanner;

public class AddGuestInRoomAction extends AAction {

    private static final Logger LOGGER = Logger.getLogger(AddGuestInRoomAction.class.getName());

    @Override
    public void execute() {
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
        try {
            LocalDate dateOut = LocalDate.parse(outDate, DateTimeFormatter.ofPattern("ddMMyyyy"));
            hotelFacade.checkIn(guestId, roomId, dateOut);
            System.out.println("The guest is checked into the room!");
        } catch (DateTimeParseException ex) {
            LOGGER.warn(ex.getMessage(), ex);
            System.err.println("Date entered incorrectly!");
        } catch (EntityNotFoundException ex) {
            LOGGER.warn(ex.getMessage(), ex);
            System.err.println(ex.getMessage());
        }
    }
}

