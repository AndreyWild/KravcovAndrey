package com.senla.util.sorter.maintenance;

import com.senla.model.Maintenance;

import java.util.Comparator;

public class MaintPriceComparator implements Comparator<Maintenance> {

    @Override
    public int compare(Maintenance m1, Maintenance m2) {
        return (int) (m1.getPrice() - m2.getPrice());
    }
}
