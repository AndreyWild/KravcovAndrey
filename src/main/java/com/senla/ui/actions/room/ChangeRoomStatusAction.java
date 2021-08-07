package com.senla.ui.actions.room;

import com.senla.model.enums.RoomStatus;
import com.senla.ui.actions.AAction;
import com.senla.util.GlobalScanner;
import com.senla.util.exceptions.EntityNotFoundException;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class ChangeRoomStatusAction extends AAction {

    private static final Logger LOGGER = Logger.getLogger(ChangeRoomStatusAction.class.getName());

    @Override
    public void execute() {
        Scanner scanner = GlobalScanner.getInstance();
        System.out.print("Enter Id room: ");
        Long roomId = scanner.nextLong();
        System.out.print("Enter status room(open/closed/repair): ");
        String roomStatus = scanner.next().toUpperCase();
        try {
            RoomStatus status = RoomStatus.valueOf(roomStatus);
            hotelFacade.changeNumberStatus(roomId, status);
            System.out.printf("Room id: %s - status: %s%n", roomId, roomStatus);
        } catch (EntityNotFoundException | IllegalArgumentException ex) {
            LOGGER.warn(ex.getMessage(), ex);
            System.err.println(ex.getMessage());
        }

    }
}
