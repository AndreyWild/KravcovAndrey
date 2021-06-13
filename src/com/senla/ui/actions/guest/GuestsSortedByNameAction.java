package com.senla.ui.actions.guest;

import com.senla.ui.actions.AAction;
import com.senla.util.sorter.guests.GuestNameComparator;

public class GuestsSortedByNameAction extends AAction {
    @Override
    public void execute() {
        if (hotelFacade.getAllGuests(new GuestNameComparator()).isEmpty()) {
            System.out.println("-=There are no settled guests!=-");
        } else {
            hotelFacade.getAllGuests(new GuestNameComparator()).forEach(System.out::println);
        }
    }
}
