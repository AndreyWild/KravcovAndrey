package com.senla.ui.menu;

import com.senla.ui.actions.guest.*;
import com.senla.ui.actions.maintenance.CreateMaintenanceAction;
import com.senla.ui.actions.maintenance.MaintenancesAction;
import com.senla.ui.actions.maintenance.MaintenancesSortedByNameAction;
import com.senla.ui.actions.maintenance.MaintenancesSortedByPriceAction;
import com.senla.ui.actions.room.*;
import com.senla.ui.actions.ExitAction;

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
        mainMenu.addItem(new MenuItem("GUEST MENU", guestMenu));
        mainMenu.addItem(new MenuItem("ROOM MENU", roomMenu));
        mainMenu.addItem(new MenuItem("MAINTENANCE MENU", maintenanceMenu));
        mainMenu.addItem(new MenuItem("EXIT", new ExitAction(), mainMenu));

        guestMenu.addItem(new MenuItem("* return to main menu", mainMenu));
        roomMenu.addItem(new MenuItem("* return to main menu", mainMenu));
        maintenanceMenu.addItem(new MenuItem("* return to main menu", mainMenu));

// ---------------------- GUEST MENU ---------------------------------------------
        guestMenu.addItem(new MenuItem("* creating a guest", new CreateGuestAction(), guestMenu));
        guestMenu.addItem(new MenuItem("* show guest", new GuestGetById(), guestMenu));
        guestMenu.addItem(new MenuItem("* list all guests", new AllGuestAction(), guestMenu));
        guestMenu.addItem(new MenuItem("* list all guests sorted by name", new GuestsSortedByNameAction(), guestMenu));
        guestMenu.addItem(new MenuItem("* list all guests sorted by check out date", new GuestsSortedByCheckOutDateAction(), guestMenu));
        guestMenu.addItem(new MenuItem("* show guest check", new GetBillAction(), guestMenu));
        guestMenu.addItem(new MenuItem("* order maintenance", new OrderMaintenanceAction(), guestMenu));
        guestMenu.addItem(new MenuItem("* total number of guests", new TotalNumberOfGuestsAction(), guestMenu));
        guestMenu.addItem(new MenuItem("* guest maintenances", new GuestMaintenancesAction(), guestMenu));
        guestMenu.addItem(new MenuItem("* guest maintenances sorted by name", new GuestMaintenancesSortedByNameAction(), guestMenu));
        guestMenu.addItem(new MenuItem("* guest maintenances sorted by price", new GuestMaintenancesSortedByPriceAction(), guestMenu));

// ---------------------- ROOM MENU ---------------------------------------------
        roomMenu.addItem(new MenuItem("* creating a room", new CreateRoomAction(), roomMenu));
        roomMenu.addItem(new MenuItem("* list all rooms", new RoomsAction(), roomMenu));
        roomMenu.addItem(new MenuItem("* check in the guest in the room", new AddGuestInRoomAction(), roomMenu));
        roomMenu.addItem(new MenuItem("* evict a guest", new EvictGuestFromRoomAction(), roomMenu));
        roomMenu.addItem(new MenuItem("* show rooms sorted by capacity", new RoomsSortedByCapacityAction(), roomMenu));
        roomMenu.addItem(new MenuItem("* show rooms sorted by price", new RoomsSortedByPriceAction(), roomMenu));
        roomMenu.addItem(new MenuItem("* show rooms sorted by stars", new RoomsSortedByStarsAction(), roomMenu));
        roomMenu.addItem(new MenuItem("* show open rooms sorted by capacity", new OpenRoomsSortedByCapacityAction(), roomMenu));
        roomMenu.addItem(new MenuItem("* show open rooms sorted by price", new RoomsSortedByPriceAction(), roomMenu));
        roomMenu.addItem(new MenuItem("* show open rooms sorted by stars", new OpenRoomsSortedByStarsAction(), roomMenu));
        roomMenu.addItem(new MenuItem("* number of free rooms", new NumberOfAvailableRoomsAction(), roomMenu));
        roomMenu.addItem(new MenuItem("* last three guests in room", new ThreeLastGuestsAction(), roomMenu));
        roomMenu.addItem(new MenuItem("* show room details", new RoomGetByIdAction(), roomMenu));
        roomMenu.addItem(new MenuItem("* available numbers for the date", new AvailableRoomsForDateAction(), roomMenu));

// ---------------------- MAINTENANCE MENU---------------------------------------------
        maintenanceMenu.addItem(new MenuItem("* creating a maintenance", new CreateMaintenanceAction(), maintenanceMenu));
        maintenanceMenu.addItem(new MenuItem("* list all maintenances", new MaintenancesAction(), maintenanceMenu));
        maintenanceMenu.addItem(new MenuItem("* maintenances sorted by name", new MaintenancesSortedByNameAction(), maintenanceMenu));
        maintenanceMenu.addItem(new MenuItem("* maintenances sorted by price", new MaintenancesSortedByPriceAction(), maintenanceMenu));

        this.rootMenu = mainMenu;
    }
}
