package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;
import com.senla.util.GlobalScanner;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AvailableRoomsForDateAction extends AAction {

    private static final Logger LOGGER = Logger.getLogger(AvailableRoomsForDateAction.class.getName());

    @Override
    public void execute() {
        if (CheckingListForEmptiness.roomsListEmpty()) {
            return;
        }
        Scanner scanner = GlobalScanner.getInstance();
        System.out.print("Enter the check-out date in the format (ddMMyyyy): ");
        String date = scanner.nextLine();
        try {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("ddMMyyyy"));
            hotelFacade.getAvailableRoomsForDate(localDate)
                    .forEach(System.out::println);
        } catch (DateTimeParseException ex) {
            LOGGER.warn(ex.getMessage(), ex);
            System.err.println("Date entered incorrectly!");
        }
    }
}
