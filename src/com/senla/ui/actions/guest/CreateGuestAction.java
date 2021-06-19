package com.senla.ui.actions.guest;

import com.senla.ui.actions.AAction;
import com.senla.util.GlobalScanner;

import java.util.Scanner;

public class CreateGuestAction extends AAction {
    @Override
    public void execute() {
        Scanner scanner = GlobalScanner.getInstance();
        System.out.print("Enter the name of the guest: ");
        String name = scanner.nextLine();
        System.out.print("Enter the age of the guest: ");
        Integer age = scanner.nextInt();
        hotelFacade.addGuest(name, age);
        System.out.println("-=A guest named " + name + " and age " + age + " is created!=-");
    }
}
