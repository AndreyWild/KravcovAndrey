package com.senla.api.service;

import com.senla.model.Guest;
import com.senla.model.Maintenance;

import java.util.Comparator;
import java.util.List;

public interface IGuestService {

    Guest addGuest(String name, Integer age);

    List<Guest> getAll();

    List<Guest> getAll(Comparator<Guest> comp);

    public List<Guest> getAllSortedByDateOut(Comparator<Guest> comp);

    Double getInvoiceForRoomAndMaintenances(Long guestId);

    void orderMaintenance(Long guestId, Long maintenanceId);

    List<Maintenance> getAllMaintenancesGuest(Long guestId);

    List<Maintenance> getAllMaintenancesGuest(Long guestId, Comparator<Maintenance> comp);

    Guest getById(Long guestId);

    List<Guest> getTotalNumberOfGuests();
}
