package com.senla.ui.menu;

import com.senla.model.Guest;
import com.senla.ui.actions.AddRoomAction;
import com.senla.ui.actions.GetAllRoomAction;

public class Builder {

    private Menu rootMenu;

    public Menu getRootMenu() {
        return rootMenu;
    }

    public void buildMenu() {
        Menu mainMenu = new Menu("Main menu:");
        Menu guestMenu = new Menu("Guest menu: ");
        Menu roomMenu = new Menu("Room menu: ");
        Menu maintenanceMenu = new Menu("Maintenance: ");

        mainMenu.addItem(new MenuItem("Guest menu", guestMenu));
        mainMenu.addItem(new MenuItem("Room menu", roomMenu));
        mainMenu.addItem(new MenuItem("Maintenance", maintenanceMenu));

        roomMenu.addItem(new MenuItem("Создание комнаты", new AddRoomAction(), mainMenu));
        roomMenu.addItem(new MenuItem("Вывести список", new GetAllRoomAction(), mainMenu));

        guestMenu.addItem(new MenuItem("Return to main menu", mainMenu));
        roomMenu.addItem(new MenuItem("Return to main menu", mainMenu));
        maintenanceMenu.addItem(new MenuItem("Return to main menu", mainMenu));

        this.rootMenu = mainMenu;
    }


}
