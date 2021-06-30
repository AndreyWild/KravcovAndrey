package com.senla.ui.actions;

import com.senla.util.GlobalScanner;

public class ExitAction extends AAction {
    @Override
    public void execute() {
        hotelFacade.saveGuestsToFile();
        hotelFacade.saveRoomsToFile();
        hotelFacade.saveMaintenancesToFile();
        GlobalScanner.close();
        System.out.println("The program has been completed!");
        System.exit(0);
    }
}
