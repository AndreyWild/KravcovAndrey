package com.senla.ui.actions.guest;

import com.senla.service.GuestService;
import com.senla.ui.actions.IAction;

public abstract class AGuestAction implements IAction {

    protected GuestService guestService = GuestService.getINSTANCE();
}
