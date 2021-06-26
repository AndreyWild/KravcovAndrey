package com.senla.service;

import com.senla.api.dao.IGuestDao;
import com.senla.api.service.IGuestService;
import com.senla.model.Guest;
import com.senla.model.Maintenance;
import com.senla.util.InitializerDAO;
import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GuestService implements IGuestService {

    private static final Logger LOGGER = Logger.getLogger(GuestService.class.getName());

    private final IGuestDao guestDao = InitializerDAO.GUEST_DAO;
    private final MaintenanceService maintenanceService = MaintenanceService.getInstance();

    private GuestService() {
    }

    private static GuestService instance;

    public static GuestService getInstance() {
        return Objects.requireNonNullElse(instance, new GuestService());
    }

    static {
        GuestService guestService = GuestService.getInstance();
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
        guestDao.save(guest);
        return guest;
    }

    @Override
    public List<Guest> getAll() {
        return guestDao.getAll();
    }

    @Override
    public List<Guest> getAll(Comparator<Guest> comp) {
        return guestDao.getAll()
                .stream()
                .sorted(comp)
                .collect(Collectors.toList());
    }

    @Override
    public List<Guest> getGuestsSortedByDateOut(Comparator<Guest> comp) {
        return guestDao.getAll()
                .stream()
                .filter(guest -> guest.getOut() != null)
                .sorted(comp)
                .collect(Collectors.toList());

    }

    @Override
    public Double getInvoiceForRoomAndMaintenances(Long guestId) {
        LOGGER.info(String.format("Launch getInvoiceForRoomAndMaintenances(%s)", guestId));
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
        LOGGER.info(String.format("Launch orderMaintenance", guestId, maintenanceId));
        Maintenance maintenance = maintenanceService.getMaintenanceById(maintenanceId);
        guestDao.getById(guestId).getMaintenances().add(maintenance);
    }

    @Override
    public List<Maintenance> getAllMaintenancesGuest(Long guestId) {
        LOGGER.info(String.format("Launch getAllMaintenancesGuest(%s)", guestId));
        return guestDao.getById(guestId).getMaintenances();
    }

    @Override
    public List<Maintenance> getAllMaintenancesGuest(Long guestId, Comparator<Maintenance> comp) {
        LOGGER.info(String.format("Launch getAllMaintenancesGuest(%s)", guestId));
        return guestDao.getById(guestId).getMaintenances()
                .stream()
                .sorted(comp)
                .collect(Collectors.toList());
    }

    @Override
    public Guest getGuestById(Long guestId) {
        LOGGER.info(String.format("Launch getGuestById(%s)", guestId));
        return guestDao.getById(guestId);
    }

    @Override
    public List<Guest> getTotalNumberOfGuests() {
        return guestDao.getAll()
                .stream()
                .filter(guest -> guest.getRoom() != null)
                .collect(Collectors.toList());
    }

    @Override
    public Guest update(Guest entity) {
        return guestDao.update(entity);
    }
}
