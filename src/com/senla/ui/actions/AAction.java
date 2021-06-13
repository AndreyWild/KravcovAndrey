package com.senla.ui.actions;

import com.senla.facade.HotelFacade;

public abstract class AAction implements IAction {

    protected HotelFacade hotelFacade = HotelFacade.getInstance();
}
