package com.senla.ui.actions.maintenance;

public class ShowAllMaintenanceAction extends AMaintenanceAction{
    @Override
    public void execute() {
        maintenanceService.getAllMaintenances().forEach(System.out::println);
    }
}
