package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AvailableRoomsForDateAction extends AAction {
    @Override
    public void execute() {
        if (CheckingListForEmptiness.roomsListEmpty()) {
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the check-out date in the format (ddMMyyyy): ");
        String date = scanner.nextLine();

        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("ddMMyyyy"));
        hotelFacade.getAvailableRoomsForDate(localDate)
                .forEach(System.out::println);
    }
}
