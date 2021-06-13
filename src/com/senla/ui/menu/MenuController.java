package com.senla.ui.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuController {

    private Builder builder;
    private Navigator navigator;

    public MenuController(Builder builder, Navigator navigator) {
        this.builder = builder;
        this.navigator = navigator;
    }

    public void run() {
        builder.buildMenu();
        navigator.setCurrentMenu(builder.getRootMenu());
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                navigator.printMenu();
                System.out.print("Enter the number: ");
                navigator.navigate(scanner.nextInt());
            } catch (NumberFormatException | InputMismatchException | IndexOutOfBoundsException ex) {
                System.err.println("Invalid command!");
            }
        }
    }
}
