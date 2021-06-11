package com.senla.ui.actions.guest;

public class ShowTotalNumberOfGuestsAction extends AGuestAction{
    @Override
    public void execute() {
        if(guestService.getTotalNumberOfGuests() == null){
            System.out.println("No accommodated guests!");
        } else {
            System.out.println(guestService.getTotalNumberOfGuests().size() + " guests");
            guestService.getTotalNumberOfGuests().forEach(System.out::println);
        }
    }
}
