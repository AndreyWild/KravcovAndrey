package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;

import java.util.Scanner;

public class CreateRoomAction extends AAction {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the room number: ");
        Integer number = scanner.nextInt();
        if (number < 0 | number > 9999) {
            System.err.println("-=The number cannot be negative and cannot exceed 9999!=-");
            return;
        }
        System.out.print("Enter number of seats in the room: ");
        Integer capacity = scanner.nextInt();
        if (capacity < 0 | capacity > 4) {
            System.err.println("-=Room capacity from 1 to 4!=-");
            return;
        }
        System.out.print("Enter the cost of living in the room: ");
        Double price = scanner.nextDouble();
        if (price < 0) {
            System.err.println("-=The price cannot be negative!=-");
            return;
        }
        System.out.print("Enter the number of stars: ");
        Integer numberOfStars = scanner.nextInt();
        if (numberOfStars < 1 | numberOfStars > 5) {
            System.err.println("-=Number of stars from 1 to 5!=-");
            return;
        }
        hotelFacade.addRoom(number, capacity, price, numberOfStars);
        System.out.println("Room " + number + " worth " + price + " has been created!");
    }
}


