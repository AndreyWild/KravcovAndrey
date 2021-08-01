package com.senla;

import com.senla.dao.GuestDao;
import com.senla.dao.MaintenanceDao;
import com.senla.dao.OrderDao;
import com.senla.dao.RoomDao;
import com.senla.model.Guest;
import com.senla.model.Maintenance;
import com.senla.model.Order;
import com.senla.model.Room;
import com.senla.model.enums.RoomStars;

import java.time.LocalDate;

public class Hotel {

    public static void main(String[] args) {
//        Builder builder = new Builder();
//        Navigator navigator = new Navigator();
//        ApplicationContext context = Application.run("com.senla");
//        MenuController menuController = context.getObject(MenuController.class);
//        menuController.run();

        Guest guest = new Guest("Petja", 28);
        GuestDao gDao = new GuestDao();
//        gDao.save(guest);
        guest.setId(1L);
        Guest guest5 = gDao.getById(5L);
        System.out.println(guest5);

//        Maintenance maintenance = new Maintenance("Уборка", 25.5);
        MaintenanceDao mDao = new MaintenanceDao();
//        mDao.save(maintenance);
//        maintenance.setId(1L);
        Maintenance maintenance5 = mDao.getById(5L);
        System.out.println(maintenance5);

//        Room room = new Room(111, 5, 120.0, RoomStars.ONE);
        RoomDao rDao = new RoomDao();
//        rDao.save(room);
//        room.setId(1L);
        Room room5 = rDao.getById(5L);
        System.out.println(room5);


//        Order order = new Order(guest, room);
        //order.getMaintenances().add(maintenance);
//        order.setCheckIn(LocalDate.of(2021, 01, 01));
//        order.setCheckOut(LocalDate.of(2021, 12, 31));
//        OrderDao oDao = new OrderDao();

//        System.out.println(order);
//        oDao.save(order);






    }
}

