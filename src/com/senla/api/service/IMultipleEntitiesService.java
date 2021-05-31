package com.senla.api.service;

import com.senla.model.AEntity;

import java.util.Comparator;
import java.util.List;

public interface IMultipleEntitiesService {

    /* Interface for working with all entities */

    List<AEntity> PricesForMaintenancesAndRooms();

    List<IPriceService> PricesForMaintenancesAndRooms(Comparator<IPriceService> comp);
}