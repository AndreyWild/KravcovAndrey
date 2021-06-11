package com.senla.ui.actions.room;

import com.senla.service.RoomService;
import com.senla.ui.actions.IAction;

public abstract class ARoomAction implements IAction {

    protected RoomService roomService = RoomService.getINSTANCE();
}
