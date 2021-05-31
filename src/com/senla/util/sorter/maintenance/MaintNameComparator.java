package com.senla.util.sorter.maintenance;

import com.senla.model.Maintenance;

import java.util.Comparator;

public class MaintNameComparator implements Comparator<Maintenance> {

    @Override
    public int compare(Maintenance m1, Maintenance m2) {
        return m1.getName().compareTo(m2.getName());
    }
}
