package com.senla.sorter.guests;

import com.senla.model.Guest;

import java.util.Comparator;

public class GuestDataOutComparator implements Comparator<Guest> {

    @Override
    public int compare(Guest guest1, Guest guest2) {
        return guest1.getOut().compareTo(guest2.getOut());
    }
}
