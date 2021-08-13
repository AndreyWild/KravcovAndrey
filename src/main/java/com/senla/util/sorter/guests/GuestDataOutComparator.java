package com.senla.util.sorter.guests;

import com.senla.model.Guest;

import java.util.Comparator;

public class GuestDataOutComparator implements Comparator<Guest> {

    @Override
    public int compare(Guest guest1, Guest guest2) {
        return guest1.getOrder().getCheckOut().compareTo(guest2.getOrder().getCheckOut());
    }
}
