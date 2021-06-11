package com.senla.ui.actions.guest;

import com.senla.util.sorter.guests.GuestNameComparator;

public class ShowGuestsSortedByNameAction extends AGuestAction{
    @Override
    public void execute() {
        guestService.getAll(new GuestNameComparator()).forEach(System.out::println);
    }
}
