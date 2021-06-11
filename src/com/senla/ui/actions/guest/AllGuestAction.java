package com.senla.ui.actions.guest;

public class AllGuestAction extends AGuestAction{
    @Override
    public void execute() {
        guestService.getAll().forEach(System.out::println);
    }
}
