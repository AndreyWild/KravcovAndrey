package com.senla.sorter.abstr;

import com.senla.api.service.IAbstractInt;

import java.util.Comparator;

public class AbstrPiceComparator implements Comparator<IAbstractInt> {

    @Override
    public int compare(IAbstractInt entity1, IAbstractInt entity2) {
        return (int) (entity1.getPrice() - entity2.getPrice());
    }
}
