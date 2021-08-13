package com.senla.service;

import com.senla.api.dao.IGuestDao;
import com.senla.api.dao.IRoomDao;
import com.senla.api.service.IGuestService;
import com.senla.model.Guest;
import com.senla.model.Maintenance;
import com.senla.util.InitializerDAO;
import com.senla.util.serialization.Serializer;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GuestService implements IGuestService {

    private static final Logger LOGGER = Logger.getLogger(GuestService.class.getName());

    private final IGuestDao guestDao = InitializerDAO.GUEST_DAO;
    private final MaintenanceService maintenanceService = MaintenanceService.getInstance();
    private final IRoomDao roomDao = InitializerDAO.ROOM_DAO;
    private final File file = new File("src/main/java/com/senla/util/serialization/fies/guests.json");
    private final Serializer serializer = new Serializer();

    private GuestService() {
        //guestDao.setList(serializer.getFromJsonFile(file, Guest.class));
    }

    private static GuestService instance;

    public static GuestService getInstance() {
        return Objects.requireNonNullElse(instance, new GuestService());
    }

//    static {
//        GuestService guestService = GuestService.getInstance();
//        guestService.addGuest("Eddard Stark", 50);
//        guestService.addGuest("Catelyn Stark", 46);
//        guestService.addGuest("Robb Stark", 26);
//        guestService.addGuest("Sansa Stark", 46);
//        guestService.addGuest("Arya Stark", 17);
//        guestService.addGuest("Bran Stark", 16);
//        guestService.addGuest("Rickon Stark", 10);
//        guestService.addGuest("Jon Snow", 25);
//        guestService.addGuest("Benjen Stark", 64);
//        guestService.addGuest("Lyanna Stark", 52);
//    }

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
//        return guestDao.getAll()
//                .stream()
//                .filter(guest -> guest.getOut() != null)
//                .sorted(comp)
//                .collect(Collectors.toList());

        //TODO rewrite
        return null;

    }

    @Override
    public Double getInvoiceForRoomAndMaintenances(Long guestId) {
//        LOGGER.info(String.format("Launch getInvoiceForRoomAndMaintenances(%s)", guestId));
//        Guest guest = guestDao.getById(guestId);
//        double bill = roomDao.getById(guest.getRoom().getId()).getPrice() +
//                guest.getMaintenances().stream()
//                        //.filter(Objects::nonNull)
//                        .mapToDouble(Maintenance::getPrice)
//                        .sum();
//        return bill;

        //TODO rewrite
        return null;
    }

    @Override
    public void orderMaintenance(Long guestId, Long maintenanceId) {
//        LOGGER.info(String.format("Launch orderMaintenance", guestId, maintenanceId));
//        Guest guest = guestDao.getById(guestId);
//        guest.getMaintenances().add(maintenanceService.getMaintenanceById(maintenanceId));
//        update(guest);
////        guestDao.getById(guestId).getMaintenances().add(maintenance);

        //TODO rewrite

    }

    @Override
    public List<Maintenance> getAllMaintenancesGuest(Long guestId) {
//        LOGGER.info(String.format("Launch getAllMaintenancesGuest(%s)", guestId));
//        return guestDao.getById(guestId).getMaintenances();
        //TODO rewrite
        return null;
    }

    @Override
    public List<Maintenance> getAllMaintenancesGuest(Long guestId, Comparator<Maintenance> comp) {
//        LOGGER.info(String.format("Launch getAllMaintenancesGuest(%s)", guestId));
//        return guestDao.getById(guestId).getMaintenances()
//                .stream()
//                .sorted(comp)
//                .collect(Collectors.toList());

        //TODO rewrite
        return null;
    }

    @Override
    public Guest getGuestById(Long guestId) {
        LOGGER.info(String.format("Launch getGuestById(%s)", guestId));
        return guestDao.getById(guestId);
    }

    @Override
    public List<Guest> getTotalNumberOfGuests() {
//        return guestDao.getAll()
//                .stream()
//                .filter(guest -> guest.getRoom() != null)
//                .collect(Collectors.toList());

        //TODO rewrite
        return null;
    }

    @Override
    public Guest update(Guest entity) {
        return guestDao.update(entity);
    }

    @Override
    public void saveToFile(){
        serializer.saveToJsonFile(file, guestDao.getAll());
    }
}
