package com.senla.ui.actions.maintenance;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;

public class MaintenancesAction extends AAction {
    @Override
    public void execute() {
        if (CheckingListForEmptiness.maintenanceListEmpty()) {
            return;
        }
        hotelFacade.getAllMaintenances().forEach(System.out::println);
    }
}
