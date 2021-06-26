package com.senla.service;

import com.senla.api.service.IPriceService;
import com.senla.api.service.IMultipleEntitiesService;
import com.senla.model.AEntity;
import com.senla.util.sorter.maintenance.MaintenancePriceComparator;
import com.senla.util.sorter.rooms.RoomsPriceComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MultipleEntitiesService implements IMultipleEntitiesService {

    private final GuestService guestService = GuestService.getInstance();
    private final RoomService roomService = RoomService.getInstance();
    private final MaintenanceService maintenanceService = MaintenanceService.getInstance();

    private MultipleEntitiesService() {
    }

    private static MultipleEntitiesService instance;

    public static MultipleEntitiesService getInstance(){
        return Objects.requireNonNullElse(instance, new MultipleEntitiesService());
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
