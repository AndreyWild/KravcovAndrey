package com.senla.service;

import com.senla.api.dao.IGuestDao;
import com.senla.api.dao.IMaintenanceDao;
import com.senla.api.dao.IRoomDao;
import com.senla.api.service.IPriceService;
import com.senla.api.service.IMultipleEntitiesService;
import com.senla.model.AEntity;
import com.senla.util.InitializerDAO;
import com.senla.util.sorter.maintenance.MaintenancePriceComparator;
import com.senla.util.sorter.rooms.RoomsPriceComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MultipleEntitiesService implements IMultipleEntitiesService {

    private final IGuestDao guestDao = InitializerDAO.GUEST_DAO;
    private final IRoomDao roomDao = InitializerDAO.ROOM_DAO;
    private final IMaintenanceDao maintenanceDao = InitializerDAO.MAINTENANCE_DAO;

    private MultipleEntitiesService() {
    }

    private static MultipleEntitiesService instance;

    public static MultipleEntitiesService getInstance(){
        return Objects.requireNonNullElse(instance, new MultipleEntitiesService());
    }

    @Override
    public List<AEntity> getPricesForMaintenancesAndRoom() {
        List<AEntity> maintenancesAndRooms = new ArrayList<>();
        maintenancesAndRooms.addAll(roomDao.getAll()
                .stream()
                .sorted(new RoomsPriceComparator())
                .collect(Collectors.toList()));
        maintenancesAndRooms.addAll(maintenanceDao.getAll()
                .stream().sorted(new MaintenancePriceComparator())
                .collect(Collectors.toList()));
        return maintenancesAndRooms;
    }

    @Override
    public List<IPriceService> getPricesForMaintenancesAndRoom(Comparator<IPriceService> comp) {
        List<IPriceService> maintenancesAndRooms = new ArrayList<>();
        maintenancesAndRooms.addAll(roomDao.getAll());
        maintenancesAndRooms.addAll(maintenanceDao.getAll());
        maintenancesAndRooms.sort(comp);
        return maintenancesAndRooms;
    }
}
