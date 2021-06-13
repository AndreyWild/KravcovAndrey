package com.senla.ui.actions.guest;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;

public class TotalNumberOfGuestsAction extends AAction {
    @Override
    public void execute() {
        if(CheckingListForEmptiness.guestsListEmpty()){
            return;
        }
        if (hotelFacade.getTotalNumberOfGuests() == null) {
            System.out.println("-=No accommodated guests!=-");
            return;
        }
        System.out.println(hotelFacade.getTotalNumberOfGuests().size() + " guests");
        hotelFacade.getTotalNumberOfGuests().forEach(System.out::println);
    }
}
