package com.senla.ui.actions.guest;

import com.senla.ui.actions.AAction;

public class TotalNumberOfGuestsAction extends AAction {
    @Override
    public void execute() {
        if(hotelFacade.getTotalNumberOfGuests() == null){
            System.out.println("-=No accommodated guests!=-");
        } else {
            System.out.println(hotelFacade.getTotalNumberOfGuests().size() + " guests");
            hotelFacade.getTotalNumberOfGuests().forEach(System.out::println);
        }
    }
}
