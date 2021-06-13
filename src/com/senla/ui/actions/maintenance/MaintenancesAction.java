package com.senla.ui.actions.maintenance;

import com.senla.ui.actions.AAction;

public class MaintenancesAction extends AAction {
    @Override
    public void execute() {
        hotelFacade.getAllMaintenances().forEach(System.out::println);
    }
}
