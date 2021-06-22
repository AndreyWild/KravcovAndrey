package com.senla.ui.actions.guest;

import com.senla.ui.actions.AAction;
import com.senla.util.GlobalScanner;
import com.senla.util.exceptions.ServiceEntityNotFoundException;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class GetBillAction extends AAction {

    private static final Logger LOGGER = Logger.getLogger(GetBillAction.class.getName());

    @Override
    public void execute() {
        try {
            Scanner scanner = GlobalScanner.getInstance();
            System.out.print("Enter the guest ID to receive an invoice for the room and services: ");
            Long guestId = scanner.nextLong();
            if (hotelFacade.getGuestById(guestId) == null) {
                System.out.println("There is no guest with this index!");
                return;
            }
            if (hotelFacade.getGuestById(guestId).getRoom() == null) {
                System.out.println(hotelFacade.getGuestById(guestId).getName() + " not settled!");
            } else {
                System.out.println(hotelFacade.getGuestById(guestId).getName() + " have to pay " +
                        hotelFacade.getInvoiceForRoomAndMaintenances(guestId) + "$");
            }
        } catch (ServiceEntityNotFoundException ex) {
            LOGGER.warn(ex.getMessage(), ex);
            System.err.println(ex.getMessage());
        }
    }
}
