package com.senla.ui.actions.guest;

import com.senla.util.sorter.guests.GuestDataOutComparator;

public class GuestsSortedByCheckOutDateAction extends AGuestAction{
    @Override
    public void execute() {
        guestService.getAllSortedByDateOut(new GuestDataOutComparator()).forEach(System.out::println);
    }
}
