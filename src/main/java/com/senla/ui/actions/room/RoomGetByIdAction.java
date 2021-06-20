package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;
import com.senla.util.CheckingListForEmptiness;
import com.senla.util.GlobalScanner;
import com.senla.util.exceptions.ServiceException;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class RoomGetByIdAction extends AAction {

    private static final Logger LOGGER = Logger.getLogger(RoomGetByIdAction.class.getName());

    @Override
    public void execute() {
        try {
            if (CheckingListForEmptiness.roomsListEmpty()) {
                return;
            }
            Scanner scanner = GlobalScanner.getInstance();
            System.out.print("Enter id room: ");
            Long roomId = scanner.nextLong();
            System.out.println(hotelFacade.getRoomById(roomId));
        } catch (ServiceException ex) {
            LOGGER.warn(ex.getMessage(), ex);
            System.err.println(ex.getMessage());
        }
    }
}
