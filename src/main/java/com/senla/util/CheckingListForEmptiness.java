package com.senla.util;


import com.senla.facade.HotelFacade;
import com.senla.facade.HotelFacadeDispenser;

public class CheckingListForEmptiness {

    private static HotelFacade hotelFacade = HotelFacadeDispenser.getHotelFacade();

    public static boolean guestsListEmpty() {
        if (hotelFacade.getAllGuests().isEmpty()) {
            System.err.println("-=No guests have been created!=-");
            return true;
        }
        return false;
    }

    public static boolean roomsListEmpty() {
        if (hotelFacade.getAllRooms().isEmpty()) {
            System.err.println("-=No rooms have been created!=-");
            return true;
        }
        return false;
    }

    public static boolean maintenanceListEmpty() {
        if (hotelFacade.getAllMaintenances().isEmpty()) {
            System.err.println("-=No maintenance have been created!=-");
            return true;
        }
        return false;
    }

    public static boolean guestMaintenancesListEmpty(Long guestId) {
        if (hotelFacade.getAllMaintenancesGuest(guestId).isEmpty()) {
            System.out.println("-=The guest did not order services!=-");
            return true;
        }
        return false;
    }

}
