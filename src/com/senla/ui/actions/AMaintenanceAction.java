package com.senla.ui.actions;

import com.senla.service.MaintenanceService;

public abstract class AMaintenanceAction implements IAction{

    MaintenanceService maintenanceService = MaintenanceService.getINSTANCE();
}
