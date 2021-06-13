package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;

import java.util.Scanner;

public class AddRoomAction extends AAction {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the room number: ");
        Integer number = scanner.nextInt();
        System.out.print("Enter number of seats in the room: ");
        Integer capacity = scanner.nextInt();
        System.out.print("Enter the cost of living in the room: ");
        Double price = scanner.nextDouble();
        System.out.print("Enter the number of stars: ");
        Integer numberOfStars = scanner.nextInt();
        hotelFacade.addRoom(number, capacity, price, numberOfStars);
        System.out.println("Room " + number + " worth " + price + " has been created!");

    }
}


