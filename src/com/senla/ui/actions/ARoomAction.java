package com.senla.ui.actions;

import com.senla.service.RoomService;

public abstract class ARoomAction implements IAction{

    protected RoomService roomService = RoomService.getINSTANCE();
}
