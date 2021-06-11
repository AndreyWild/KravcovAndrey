package com.senla.ui.menu;

import com.senla.ui.actions.guest.*;
import com.senla.ui.actions.maintenance.AddMaintenanceAction;
import com.senla.ui.actions.maintenance.ShowAllMaintenanceAction;
import com.senla.ui.actions.room.AddRoomAction;
import com.senla.ui.actions.ExitAction;
import com.senla.ui.actions.room.ShowAllRoomAction;

public class Builder {

    private Menu rootMenu;

    public Menu getRootMenu() {
        return rootMenu;
    }

    public void buildMenu() {
        Menu mainMenu = new Menu("MAIN MENU:");
        Menu guestMenu = new Menu("GUEST MENU: ");
        Menu roomMenu = new Menu("ROOM MENU: ");
        Menu maintenanceMenu = new Menu("MAINTENANCE: ");

// ---------------------- MAIN MENU ---------------------------------------------
        mainMenu.addItem(new MenuItem("guest menu", guestMenu));
        mainMenu.addItem(new MenuItem("room menu", roomMenu));
        mainMenu.addItem(new MenuItem("maintenance menu", maintenanceMenu));
        mainMenu.addItem(new MenuItem("exit", new ExitAction(), mainMenu));

        guestMenu.addItem(new MenuItem("return to main menu", mainMenu));
        roomMenu.addItem(new MenuItem("return to main menu", mainMenu));
        maintenanceMenu.addItem(new MenuItem("return to main menu", mainMenu));

// ---------------------- GUEST MENU ---------------------------------------------
        guestMenu.addItem(new MenuItem("creating a guest", new AddGuestAction(), guestMenu));
        guestMenu.addItem(new MenuItem("list all guests", new AllGuestAction(), guestMenu));
        guestMenu.addItem(new MenuItem("list all guests sorted by name", new ShowGuestsSortedByNameAction(), guestMenu));
        guestMenu.addItem(new MenuItem("list all guests sorted by check out date", new GuestsSortedByCheckOutDateAction(), guestMenu));
        guestMenu.addItem(new MenuItem("show guest check", new BillForRoomAndMaintenancesByIdAction(), guestMenu));
        guestMenu.addItem(new MenuItem("order maintenance", new OrderMaintenanceAction(), guestMenu));
        guestMenu.addItem(new MenuItem("total number of guests", new ShowTotalNumberOfGuestsAction(), guestMenu));
        guestMenu.addItem(new MenuItem("guest maintenances", new GuestMaintenancesAction(), guestMenu));
        guestMenu.addItem(new MenuItem("guest maintenances sorted by name", new GuestMaintenancesSortedByNameAction(), guestMenu));
        guestMenu.addItem(new MenuItem("guest maintenances sorted by price", new GuestMaintenancesSortedByPriceAction(), guestMenu));

// ---------------------- ROOM MENU ---------------------------------------------
        roomMenu.addItem(new MenuItem("creating a room", new AddRoomAction(), roomMenu));
        roomMenu.addItem(new MenuItem("list all rooms", new ShowAllRoomAction(), roomMenu));

// ---------------------- MAINTENANCE ---------------------------------------------
        maintenanceMenu.addItem(new MenuItem("creating a maintenance", new AddMaintenanceAction(), maintenanceMenu));
        maintenanceMenu.addItem(new MenuItem("list all maintenances", new ShowAllMaintenanceAction(), maintenanceMenu));

        this.rootMenu = mainMenu;
    }


}
