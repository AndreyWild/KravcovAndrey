package com.senla.ui.menu;

import java.util.Scanner;

public class MenuController {

    private Builder builder;
    private Navigator navigator;

    public MenuController(Builder builder, Navigator navigator) {
        this.builder = builder;
        this.navigator = navigator;
    }

    public void run(){
        builder.buildMenu();
        navigator.setCurrentMenu(builder.getRootMenu());
        while (true){
            navigator.printMenu();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the number: ");
            navigator.navigate(scanner.nextInt());
        }
    }
}
