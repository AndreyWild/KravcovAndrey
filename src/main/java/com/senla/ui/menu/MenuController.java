package com.senla.ui.menu;

import com.senla.ui.actions.room.ThreeLastGuestsAction;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuController {

    private static final Logger LOGGER = Logger.getLogger(MenuController.class.getName());

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
                LOGGER.warn(ex.getMessage(), ex);
                System.err.println("Invalid command!");
            }
        }
    }
}
