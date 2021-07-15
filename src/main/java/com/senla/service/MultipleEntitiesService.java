package com.senla.service;

import com.senla.api.service.IPriceService;
import com.senla.api.service.IMultipleEntitiesService;
import com.senla.model.AEntity;
import com.senla.my_spring.annotations.Autowired;
import com.senla.my_spring.annotations.Singleton;
import com.senla.util.sorter.maintenance.MaintenancePriceComparator;
import com.senla.util.sorter.rooms.RoomsPriceComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class MultipleEntitiesService implements IMultipleEntitiesService {

    @Autowired
    private GuestService guestService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private MaintenanceService maintenanceService;

    private MultipleEntitiesService() {
    }

    @Override
    public List<AEntity> getPricesForMaintenancesAndRoom() {
        List<AEntity> maintenancesAndRooms = new ArrayList<>();
        maintenancesAndRooms.addAll(roomService.getAll()
                .stream()
                .sorted(new RoomsPriceComparator())
                .collect(Collectors.toList()));
        maintenancesAndRooms.addAll(maintenanceService.getAll()
                .stream().sorted(new MaintenancePriceComparator())
                .collect(Collectors.toList()));
        return maintenancesAndRooms;
    }

    @Override
    public List<IPriceService> getPricesForMaintenancesAndRoom(Comparator<IPriceService> comp) {
        List<IPriceService> maintenancesAndRooms = new ArrayList<>();
        maintenancesAndRooms.addAll(roomService.getAll());
        maintenancesAndRooms.addAll(maintenanceService.getAll());
        maintenancesAndRooms.sort(comp);
        return maintenancesAndRooms;
    }
}
