package com.senla.api.service;

import com.senla.model.Maintenance;

import java.util.List;

public interface IMaintenanceService { // Интерфейс методов работы с Maintenance

    Maintenance addMaintenance(String name, Double price);

    List<Maintenance> getAllMaintenances();
}
