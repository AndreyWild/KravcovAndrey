package com.senla.ui.actions.guest;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;

public class AllGuestAction extends AAction {
    @Override
    public void execute() {
        if (CheckingListForEmptiness.guestsListEmpty()) {
            return;
        }
        hotelFacade.getAllGuests().forEach(System.out::println);

    }
}
