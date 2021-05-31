package com.senla.service;

import com.senla.api.dao.IGuestDao;
import com.senla.api.dao.IMaintenanceDao;
import com.senla.api.dao.IRoomDao;
import com.senla.api.service.IPriceService;
import com.senla.api.service.IMultipleEntitiesService;
import com.senla.model.AEntity;
import com.senla.util.sorter.maintenance.MaintPriceComparator;
import com.senla.util.sorter.rooms.RoomsPriceComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MultipleEntitiesService implements IMultipleEntitiesService {

    private final IGuestDao guestDao;
    private final IRoomDao roomDao;
    private final IMaintenanceDao maintenanceDao;

    public MultipleEntitiesService(IGuestDao guestDao, IRoomDao roomDao, IMaintenanceDao maintenanceDao) {
        this.guestDao = guestDao;
        this.roomDao = roomDao;
        this.maintenanceDao = maintenanceDao;
    }

    @Override
    public List<AEntity> PricesForMaintenancesAndRooms() {
        List<AEntity> maintenancesAndRooms = new ArrayList<>();
        maintenancesAndRooms.addAll(roomDao.getAll()
                .stream()
                .sorted(new RoomsPriceComparator())
                .collect(Collectors.toList()));
        maintenancesAndRooms.addAll(maintenanceDao.getAll()
                .stream().sorted(new MaintPriceComparator())
                .collect(Collectors.toList()));

        return maintenancesAndRooms;
    }

    @Override
    public List<IPriceService> PricesForMaintenancesAndRooms(Comparator<IPriceService> comp) {
        List<IPriceService> maintenancesAndRooms = new ArrayList<>();
        maintenancesAndRooms.addAll(roomDao.getAll());
        maintenancesAndRooms.addAll(maintenanceDao.getAll());
        maintenancesAndRooms.sort(comp);

        return maintenancesAndRooms;
    }
}
