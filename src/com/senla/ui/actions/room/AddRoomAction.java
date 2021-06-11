package com.senla.ui.actions.room;

import com.senla.util.ScannerInit;

import java.util.Scanner;

public class AddRoomAction extends ARoomAction {
    @Override
    public void execute() {
        Scanner scanner = ScannerInit.getINSTANCE();
        System.out.print("Enter the room number: ");
        Integer number = scanner.nextInt();
        System.out.print("Enter number of seats in the room: ");
        Integer capacity = scanner.nextInt();
        System.out.print("Enter the cost of living in the room: ");
        Double price = scanner.nextDouble();
        System.out.print("Enter the number of stars: ");
        Integer numberOfStars = scanner.nextInt();
        roomService.addRoom(number, capacity, price, numberOfStars);
        System.out.println("Room " + number + " worth " + price + " has been created!");

    }
}


