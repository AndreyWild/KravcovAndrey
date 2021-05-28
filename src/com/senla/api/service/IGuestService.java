package com.senla.api.service;

import com.senla.model.Guest;
import com.senla.model.Maintenance;

import java.util.Comparator;
import java.util.List;

public interface IGuestService {

    Guest addGuest(String name, Integer age);

    List<Guest> getAllGuests();

    List<Guest> getAllGuests(Comparator<Guest> comp);

    Double roomBill(Guest guest);

    void orderMaintenance(Long guestId, Long maintenanceId);

    List<Maintenance> getAllMaintenancesGuest(Long guestId, Comparator<Maintenance> comp);
}
