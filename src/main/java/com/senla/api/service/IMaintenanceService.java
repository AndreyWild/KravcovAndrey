package com.senla.api.service;

import com.senla.model.Maintenance;

import java.util.Comparator;
import java.util.List;

public interface IMaintenanceService {

    Maintenance addMaintenance(String name, Double price);

    List<Maintenance> getAll();

    Maintenance getMaintenanceById(Long maintenanceId);

    List<Maintenance> getAll(Comparator<Maintenance> comp);

    void saveToFile();
}
