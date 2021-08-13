package com.senla;

import com.senla.api.dao.IGuestDao;
import com.senla.api.dao.IMaintenanceDao;
import com.senla.api.dao.IOrderDao;
import com.senla.api.dao.IRoomDao;
import com.senla.dao.GuestDao;
import com.senla.dao.MaintenanceDao;
import com.senla.dao.OrderDao;
import com.senla.dao.RoomDao;
import com.senla.model.Guest;
import com.senla.model.Maintenance;
import com.senla.model.Order;
import com.senla.model.Room;
import com.senla.model.enums.RoomStars;
import com.senla.my_spring.configurations.Application;
import com.senla.my_spring.configurations.ApplicationContext;
import com.senla.ui.menu.Builder;
import com.senla.ui.menu.MenuController;
import com.senla.ui.menu.Navigator;

import java.time.LocalDate;

public class Hotel {

    private static final IGuestDao guestDAO = new GuestDao();
    private static final IRoomDao roomDAO = new RoomDao();
    private static final IMaintenanceDao maintenanceDAO = new MaintenanceDao();
    private static final IOrderDao orderDAO = new OrderDao();

    public static void main(String[] args) {
//        Builder builder = new Builder();
//        Navigator navigator = new Navigator();
//        ApplicationContext context = Application.run("com.senla");
//        MenuController menuController = context.getObject(MenuController.class);
//        menuController.run();

        Guest guest = new Guest("YYY", 32);
        guest.setId(11L);
        //System.out.println(guestDAO.save(guest));

        Room room = new Room(888,3, 220.12, RoomStars.THREE);
        room.setId(3L);
        //System.out.println(roomDAO.save(room));

        Maintenance maintenance = new Maintenance("Cleaning", 300.0);
        //System.out.println(maintenanceDAO.save(maintenance));

        Order order = new Order(guest, room, LocalDate.now(), LocalDate.of(2021,12,31));
//        System.out.println(orderDAO.save(order));

//        System.out.println(guestDAO.getById(1L));
//        System.out.println(roomDAO.getById(1L));
//        System.out.println(maintenanceDAO.getById(1L));
//        System.out.println(orderDAO.getById(1L));


        System.out.println(guestDAO.update(guest));

        guestDAO.getAll().forEach(System.out::println);
        roomDAO.getAll().forEach(System.out::println);
        maintenanceDAO.getAll().forEach(System.out::println);
    }
}
