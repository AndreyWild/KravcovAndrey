package com.senla.util.sorter.abstr;

import com.senla.api.service.IPriceService;

import java.util.Comparator;

public class AbstrPiceComparator implements Comparator<IPriceService> {

    @Override
    public int compare(IPriceService entity1, IPriceService entity2) {
        return (int) (entity1.getPrice() - entity2.getPrice());
    }
}
