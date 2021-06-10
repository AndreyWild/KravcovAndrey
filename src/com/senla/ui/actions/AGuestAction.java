package com.senla.ui.actions;

import com.senla.service.GuestService;

public abstract class AGuestAction implements IAction{

    protected GuestService guestService = GuestService.getINSTANCE();
}
