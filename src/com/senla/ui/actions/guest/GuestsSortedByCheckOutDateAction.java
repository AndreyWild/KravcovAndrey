package com.senla.ui.actions.guest;

import com.senla.ui.actions.AAction;
import com.senla.util.sorter.guests.GuestDataOutComparator;

public class GuestsSortedByCheckOutDateAction extends AAction {
    @Override
    public void execute() {
        if (hotelFacade.getGuestsSortedByDateOut(new GuestDataOutComparator()).isEmpty()) {
            System.out.println("-=There are no settled guests!=-");
        } else {
            hotelFacade.getGuestsSortedByDateOut(new GuestDataOutComparator()).forEach(System.out::println);
        }
    }
}
