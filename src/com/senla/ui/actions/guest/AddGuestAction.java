package com.senla.ui.actions.guest;

import com.senla.util.ScannerInit;

import java.util.Scanner;

public class AddGuestAction extends AGuestAction {
    @Override
    public void execute() {
        Scanner scanner = ScannerInit.getINSTANCE();
        System.out.print("Enter the name of the guest: ");
        String name = scanner.nextLine();
        System.out.print("Enter the age of the guest: ");
        Integer age = scanner.nextInt();
        guestService.addGuest(name, age);
        System.out.println("A guest named " + name + " and age " + age + " is created!");
    }
}
