package com.senla.api.service;

import com.senla.model.AEntity;

import java.util.Comparator;
import java.util.List;

public interface IAbstractService {
    List<AEntity> PricesForMaintenancesAndRooms();

    List<IAbstractInt> PricesForMaintenancesAndRooms(Comparator<IAbstractInt> comp);
}
