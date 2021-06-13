package com.senla.ui.actions;


public class ExitAction extends AAction {
    @Override
    public void execute() {
        System.out.println("The program has been completed!");
        System.exit(0);
    }
}
