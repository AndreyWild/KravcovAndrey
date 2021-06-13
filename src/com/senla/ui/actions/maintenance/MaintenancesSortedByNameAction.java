package com.senla.ui.actions.maintenance;

import com.senla.ui.actions.AAction;
import com.senla.util.sorter.maintenance.MaintenanceNameComparator;

public class MaintenancesSortedByNameAction extends AAction {
    @Override
    public void execute() {
        hotelFacade.getAllMaintenances(new MaintenanceNameComparator()).forEach(System.out::println);
    }
}
