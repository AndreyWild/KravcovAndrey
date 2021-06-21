package com.senla.ui.actions;

import com.senla.util.GlobalScanner;

public class ExitAction extends AAction {
    @Override
    public void execute() {
        GlobalScanner.close();
        System.out.println("The program has been completed!");
        System.exit(0);
    }
}
