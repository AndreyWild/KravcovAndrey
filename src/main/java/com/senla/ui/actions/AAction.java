package com.senla.ui.actions;

import com.senla.facade.HotelFacade;
import com.senla.facade.HotelFacadeDispenser;

public abstract class AAction implements IAction {

    protected HotelFacade hotelFacade = HotelFacadeDispenser.getHotelFacade();
}
