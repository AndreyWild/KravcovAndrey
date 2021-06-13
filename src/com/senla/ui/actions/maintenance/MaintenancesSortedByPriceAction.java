package com.senla.ui.actions.maintenance;

import com.senla.ui.actions.AAction;
import com.senla.util.sorter.maintenance.MaintenancePriceComparator;

public class MaintenancesSortedByPriceAction extends AAction {
    @Override
    public void execute() {
        hotelFacade.getAllMaintenances(new MaintenancePriceComparator()).forEach(System.out::println);
    }
}
