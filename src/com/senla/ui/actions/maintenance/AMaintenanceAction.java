package com.senla.ui.actions.maintenance;

import com.senla.service.MaintenanceService;
import com.senla.ui.actions.IAction;

public abstract class AMaintenanceAction implements IAction {

    MaintenanceService maintenanceService = MaintenanceService.getINSTANCE();
}
