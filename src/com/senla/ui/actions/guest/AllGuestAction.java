package com.senla.ui.actions.guest;

import com.senla.ui.actions.AAction;

public class AllGuestAction extends AAction {
    @Override
    public void execute() {
        if (hotelFacade.getAllGuests().isEmpty()) {
            System.out.println("-=No guests!=-");
        } else {
            hotelFacade.getAllGuests().forEach(System.out::println);
        }
    }
}
