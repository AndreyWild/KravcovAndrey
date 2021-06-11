package com.senla.service;

import com.senla.api.dao.IGuestDao;
import com.senla.api.dao.IMaintenanceDao;
import com.senla.api.service.IGuestService;
import com.senla.dao.GuestDao;
import com.senla.dao.MaintenanceDao;
import com.senla.model.Guest;
import com.senla.model.Maintenance;
import com.senla.util.InitializerDAO;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GuestService implements IGuestService {

    private final IGuestDao guestDao = InitializerDAO.GUEST_DAO;
    private final IMaintenanceDao maintenanceDao = InitializerDAO.MAINTENANCE_DAO;

//    public GuestService(IGuestDao guestDao, IMaintenanceDao maintenanceDao) {
//        this.guestDao = guestDao;
//        this.maintenanceDao = maintenanceDao;
//    }

    private GuestService() {
    }

    private static GuestService INSTANCE;

    public static GuestService getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new GuestService();
        }
        return INSTANCE;
    }

    static {
        GuestService guestService = GuestService.getINSTANCE();
        guestService.addGuest("Eddard Stark", 50);
        guestService.addGuest("Catelyn Stark", 46);
        guestService.addGuest("Robb Stark", 26);
        guestService.addGuest("Sansa Stark", 46);
        guestService.addGuest("Arya Stark", 17);
        guestService.addGuest("Bran Stark", 16);
        guestService.addGuest("Rickon Stark", 10);
        guestService.addGuest("Jon Snow", 25);
        guestService.addGuest("Benjen Stark", 64);
        guestService.addGuest("Lyanna Stark", 52);
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
    public List<Guest> getAllSortedByDateOut(Comparator<Guest> comp) {
        List<Guest> guests = guestDao.getAll()
                .stream()
                .filter(guest -> guest.getOut() != null)
                .collect(Collectors.toList());
        return guests;
    }

    @Override
    public Double getInvoiceForRoomAndMaintenances(Long guestId) {
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
    public List<Maintenance> getAllMaintenancesGuest(Long guestId) {
        return guestDao.getById(guestId).getMaintenances();
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

    @Override
    public List<Guest> getTotalNumberOfGuests() {
        return guestDao.getAll()
                .stream()
                .filter(guest -> guest.getRoom() != null)
                .collect(Collectors.toList());
    }
}
