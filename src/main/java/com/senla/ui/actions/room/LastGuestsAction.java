package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;
import com.senla.util.GlobalScanner;
import com.senla.util.exceptions.EntityNotFoundException;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class LastGuestsAction extends AAction {

    private static final Logger LOGGER = Logger.getLogger(LastGuestsAction.class.getName());

    @Override
    public void execute() {
        Scanner scanner = GlobalScanner.getInstance();
        System.out.print("Enter id room: ");
        Long roomId = scanner.nextLong();
        try {
            hotelFacade.showLastGuests(roomId);
        } catch (EntityNotFoundException ex) {
            LOGGER.warn(ex.getMessage(), ex);
            System.err.println(ex.getMessage());
        }
    }
}
