package com.senla.ui.actions.guest;

import com.senla.ui.actions.AAction;
import com.senla.util.GlobalScanner;
import com.senla.util.exceptions.ServiceEntityNotFoundException;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrderMaintenanceAction extends AAction {

    private static final Logger LOGGER = Logger.getLogger(OrderMaintenanceAction.class.getName());

    @Override
    public void execute() {
        try {
            Scanner scanner = GlobalScanner.getInstance();
            System.out.print("Enter id guest: ");
            Long idGuest = scanner.nextLong();
            System.out.print("Enter id maintenance: ");
            Long idMaintenance = scanner.nextLong();
            hotelFacade.orderMaintenance(idGuest, idMaintenance);
            System.out.println("Maintenance added!");
        } catch (ServiceEntityNotFoundException ex) {
            LOGGER.warn(ex.getMessage(), ex);
            System.err.println(ex.getMessage());
        } catch (InputMismatchException ex) {
            LOGGER.warn(ex.getMessage(), ex);
            System.err.println("You are doing something wrong!");
        }
    }
}
