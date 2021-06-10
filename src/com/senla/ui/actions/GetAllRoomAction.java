package com.senla.ui.actions;

public class GetAllRoomAction extends ARoomAction implements IAction{
    @Override
    public void execute() {
        roomService.getAll().forEach(System.out::println);

    }
}
