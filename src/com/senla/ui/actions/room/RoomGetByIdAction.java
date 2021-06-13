package com.senla.ui.actions.room;

import com.senla.ui.actions.AAction;

import java.util.Scanner;

public class RoomGetByIdAction extends AAction {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Long roomId = scanner.nextLong();
        hotelFacade.getRoomById(roomId);
    }
}
