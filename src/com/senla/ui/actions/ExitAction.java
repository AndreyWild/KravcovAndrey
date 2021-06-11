package com.senla.ui.actions;

import com.senla.ui.actions.room.ARoomAction;
import com.senla.util.ScannerInit;

public class ExitAction extends ARoomAction {
    @Override
    public void execute() {
        System.out.println("The program has been completed!");
//        ScannerInit.closeScanner();
        System.exit(0);
    }
}
