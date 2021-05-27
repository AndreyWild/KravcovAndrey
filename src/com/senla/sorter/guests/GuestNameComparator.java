package com.senla.sorter.guests;

import com.senla.model.Guest;

import java.util.Comparator;

public class GuestNameComparator implements Comparator<Guest> {
    @Override
    public int compare(Guest guest1, Guest guest2) {
        return guest1.getName().compareTo(guest2.getName());
    }
}
