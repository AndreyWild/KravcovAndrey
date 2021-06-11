package com.senla.ui.actions.room;

import com.senla.ui.actions.IAction;

public class ShowAllRoomAction extends ARoomAction implements IAction {
    @Override
    public void execute() {
        roomService.getAll().forEach(System.out::println);

    }
}
