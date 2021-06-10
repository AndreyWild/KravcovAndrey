package com.senla.api.service;

import com.senla.model.Maintenance;

import java.util.List;

public interface IMaintenanceService {

    Maintenance addMaintenance(String name, Double price);

    List<Maintenance> getAllMaintenances();

    Maintenance getById(Long maintenanceId);

    List<Maintenance> getAll();
}
