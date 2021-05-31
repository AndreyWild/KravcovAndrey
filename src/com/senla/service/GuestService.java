package com.senla.service;

import com.senla.api.dao.IGuestDao;
import com.senla.api.dao.IMaintenanceDao;
import com.senla.api.service.IGuestService;
import com.senla.dao.GuestDao;
import com.senla.dao.MaintenanceDao;
import com.senla.model.Guest;
import com.senla.model.Maintenance;

import java.util.Comparator;
import java.util.List;

public class GuestService implements IGuestService {

    private final IGuestDao guestDao ;
    private final IMaintenanceDao maintenanceDao;

    public GuestService(IGuestDao guestDao, IMaintenanceDao maintenanceDao) {
        this.guestDao = guestDao;
        this.maintenanceDao = maintenanceDao;
    }

    @Override
    public Guest addGuest(String name, Integer age) {
        Guest guest = new Guest(name, age);
        //guest.setId(IdGenerator.generateGuestId());
        guestDao.save(guest);
        return guest;
    }

    @Override
    public List<Guest> getAll() {
        return guestDao.getAll();
    }

    @Override
    public List<Guest> getAll(Comparator<Guest> comp) {
        List<Guest> guests = guestDao.getAll();
        guests.sort(comp);

        return guests;
    }

    @Override
    public Double roomBill(Long guestId) {
        Guest guest = guestDao.getById(guestId);
        double bill = guest.getRoom().getPrice() +
                guest.getMaintenances().stream()
                        //.filter(Objects::nonNull)
                        .mapToDouble(Maintenance::getPrice)
                        .sum();
        return bill;
    }

    @Override
    public void orderMaintenance(Long guestId, Long maintenanceId) {
        Guest guest = guestDao.getById(guestId);
        Maintenance maintenance = maintenanceDao.getById(maintenanceId);
        guest.getMaintenances().add(maintenance);
    }

    @Override
    public List<Maintenance> getAllMaintenancesGuest(Long guestId, Comparator<Maintenance> comp) {
        List<Maintenance> maintenances = guestDao.getById(guestId).getMaintenances();
        maintenances.sort(comp);

        return maintenances;
    }

    @Override
    public Guest getById(Long guestId) {
        return guestDao.getById(guestId);
    }
}
