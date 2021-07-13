package com.senla.ui.menu;

import com.senla.my_spring.annotations.Autowired;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuController {

    private static final Logger LOGGER = Logger.getLogger(MenuController.class.getName());

    @Autowired
    private Builder builder;
    @Autowired
    private Navigator navigator;


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
