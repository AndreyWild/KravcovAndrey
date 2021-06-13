package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Formatter;
import java.util.Scanner;

public class AddGuestInRoomAction extends AAction {
    @Override
    public void execute() {
        try{
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);

        System.out.print("Enter the guest ID: ");
        Long guestId = scanner.nextLong();

        System.out.print("Enter the room ID: ");
        Long roomId = scanner.nextLong();

        System.out.print("Enter the check-out date in the format (ddMMyyyy): ");
        String outDate = scanner2.nextLine();

        LocalDate dateOut = LocalDate.parse(outDate, DateTimeFormatter.ofPattern("ddMMyyyy"));
        hotelFacade.checkIn(guestId, roomId, dateOut);

        System.out.println("The guest is checked into the room!");

        } catch (NullPointerException ex){
            System.err.println("There is no such ID!");
        } catch (DateTimeParseException ex){
            System.err.println("Date entered incorrectly!");
        }
    }
}

