package com.senla;

import com.senla.api.dao.IGuestDao;
import com.senla.api.dao.IMaintenanceDao;
import com.senla.api.dao.IRoomDao;
import com.senla.api.service.IAbstractService;
import com.senla.api.service.IGuestService;
import com.senla.api.service.IMaintenanceService;
import com.senla.api.service.IRoomService;
import com.senla.dao.GuestDao;
import com.senla.dao.MaintenanceDao;
import com.senla.dao.RoomDao;
import com.senla.model.*;
import com.senla.service.AbstractService;
import com.senla.service.GuestService;
import com.senla.service.MaintenanceService;
import com.senla.service.RoomService;
//import com.senla.sorter.PriceComparator;
//import com.senla.sorter.maintenances.MaintNameComparator;
//import com.senla.sorter.maintenances.MaintPriceComparator;
import com.senla.sorter.abstr.AbstrPiceComparator;
import com.senla.sorter.maintenance.MaintNameComparator;
import com.senla.sorter.maintenance.MaintPriceComparator;
import com.senla.sorter.rooms.RoomsCapacityComparator;
import com.senla.sorter.guests.GuestNameComparator;
import com.senla.sorter.rooms.RoomsPriceComparator;
import com.senla.sorter.rooms.RoomsStarsComparator;
import com.senla.util.DatePeriodGenerator;
import java.time.LocalDate;
import java.util.List;


public class Hotel {

    private static final IGuestDao GUEST_DAO = new GuestDao();
    private static final IRoomDao ROOM_DAO = new RoomDao();
    private static final IMaintenanceDao MAINTENANCE_DAO = new MaintenanceDao();

    private static final IGuestService GUEST_SERVICE = new GuestService(GUEST_DAO, MAINTENANCE_DAO);
    private static final IRoomService ROOM_SERVICE = new RoomService(ROOM_DAO, GUEST_DAO, MAINTENANCE_DAO);
    private static final IMaintenanceService MAINTENANCE_SERVICE = new MaintenanceService(MAINTENANCE_DAO);
    private static final IAbstractService ABSTRACT_SERVICE = new AbstractService(GUEST_DAO, ROOM_DAO, MAINTENANCE_DAO);

    public static void main(String[] args) {
        System.out.println("------Создаю 10-х гостей------------------------------------------------------------------");
        Guest guest1 = GUEST_SERVICE.addGuest("Eddard Stark", 50);
        Guest guest2 = GUEST_SERVICE.addGuest("Catelyn Stark", 46);
        Guest guest3 = GUEST_SERVICE.addGuest("Robb Stark", 26);
        Guest guest4 = GUEST_SERVICE.addGuest("Sansa Stark", 46);
        Guest guest5 = GUEST_SERVICE.addGuest("Arya Stark", 17);
        Guest guest6 = GUEST_SERVICE.addGuest("Bran Stark", 16);
        Guest guest7 = GUEST_SERVICE.addGuest("Rickon Stark", 10);
        Guest guest8 = GUEST_SERVICE.addGuest("Jon Snow", 25);
        Guest guest9 = GUEST_SERVICE.addGuest("Benjen Stark", 64);
        Guest guest10 = GUEST_SERVICE.addGuest("Lyanna Stark", 52);
        GUEST_SERVICE.getAllGuests().forEach(System.out::println);


        System.out.println("--------Создаю номера--------------------------------------------------------------------");
        Room room1 = ROOM_SERVICE.addRoom(358, 1, 500.0, 5);
        Room room2 = ROOM_SERVICE.addRoom(12, 2, 400.0, 4);
        Room room3 = ROOM_SERVICE.addRoom(5, 3, 350.0, 3);
        Room room4 = ROOM_SERVICE.addRoom(82, 4, 300.0, 2);
        Room room5 = ROOM_SERVICE.addRoom(108, 5, 250.0, 1);
        ROOM_SERVICE.getAllRooms().forEach(System.out::println);


        System.out.println("--------Заселяю 5-х гостей в номер и прверяю их статус-----------------------------------");
        System.out.println(GUEST_DAO.getById(1L).getGuestStatus());
        System.out.println(GUEST_DAO.getById(2L).getGuestStatus());
        ROOM_SERVICE.checkIn(1L, 5L, "2021/07/21");
        ROOM_SERVICE.checkIn(2L, 5L, "2021/09/05");
        ROOM_SERVICE.checkIn(3L, 5L, "2021/10/02");
        ROOM_SERVICE.checkIn(4L, 5L, "2021/08/15");
        ROOM_SERVICE.checkIn(5L, 5L, "2021/12/31");

        System.out.println(ROOM_DAO.getById(5L));
        System.out.println(GUEST_DAO.getById(1L));
        System.out.println(GUEST_DAO.getById(1L).getGuestStatus());
        System.out.println(GUEST_DAO.getById(2L).getGuestStatus());
        System.out.println(GUEST_DAO.getById(3L).getGuestStatus());
        System.out.println(GUEST_DAO.getById(4L).getGuestStatus());
        System.out.println(GUEST_DAO.getById(5L).getGuestStatus());

        System.out.println("---------Удаляю гостя 1 из комнаты и проверяю его статус---------------------------------");
        ROOM_SERVICE.evictGuest(1L);
        System.out.println(ROOM_DAO.getById(5L));
        System.out.println(GUEST_DAO.getById(1L).getGuestStatus());

        System.out.println("---------Проверяем клиентов--------------------------------------------------------------");
        System.out.println(GUEST_DAO.getById(1L));
        System.out.println(GUEST_DAO.getById(2L));

        System.out.println("---------Меняем статус комнаты-----------------------------------------------------------");
        room1.setStatus(RoomStatus.ON_REPAIR);
        System.out.println(ROOM_DAO.getById(1L));
        room3.setStatus(RoomStatus.CLOSED);
        System.out.println(ROOM_DAO.getById(3L));

        System.out.println("---------Создаю услугу-------------------------------------------------------------------");
        Maintenance maintenance = MAINTENANCE_SERVICE.addMaintenance("Cleaning", 50.0);
        System.out.println(MAINTENANCE_DAO.getById(1L));

        System.out.println("---------Сортировка комнат по цене-------------------------------------------------------");
        ROOM_SERVICE.getAllRooms(new RoomsPriceComparator()).forEach(System.out::println);

        System.out.println("---------Сортировка комнат по вместимости------------------------------------------------");
        ROOM_SERVICE.getAllRooms(new RoomsCapacityComparator()).forEach(System.out::println);

        System.out.println("---------Сортировка комнат по количесту звезд--------------------------------------------");
        ROOM_SERVICE.getAllRooms(new RoomsStarsComparator()).forEach(System.out::println);

        System.out.println("---------Сортировка свободных комнат по цене---------------------------------------------");
        ROOM_SERVICE.getAllOpenRooms(new RoomsPriceComparator()).forEach(System.out::println);

        System.out.println("---------Сортировка свободных комнат по вместимости--------------------------------------");
        ROOM_SERVICE.getAllOpenRooms(new RoomsCapacityComparator()).forEach(System.out::println);

        System.out.println("---------Сортировка свободных комнат по количесту звезд----------------------------------");
        ROOM_SERVICE.getAllOpenRooms(new RoomsStarsComparator()).forEach(System.out::println);

        System.out.println("---------Сортировка гостей по алфавиту---------------------------------------------------");
        GUEST_SERVICE.getAllGuests(new GuestNameComparator()).forEach(System.out::println);

        System.out.println("---------Список номеров которые будут свободны по определенной дате----------------------");
        LocalDate date = LocalDate.of(2021, 05, 25);
        LocalDate date2 = LocalDate.of(2021, 12, 31);

        room1.setBusyDates(DatePeriodGenerator.toDateList("2021/05/01", "2021/05/30"));
        room2.setBusyDates(DatePeriodGenerator.toDateList("2021/05/01", "2021/05/20"));
        room3.setBusyDates(DatePeriodGenerator.toDateList("2021/05/01", "2021/05/30"));
        room4.setBusyDates(DatePeriodGenerator.toDateList("2021/05/01", "2021/05/20"));
        room5.setBusyDates(DatePeriodGenerator.toDateList("2021/05/01", "2021/05/30"));

        ROOM_SERVICE.getAllAvailableNumbersForDate(date).forEach(System.out::println);


        System.out.println("---------Создаю еще 2 услуги, добавляю их гостю и вывожу счет за номер-------------------");
        Maintenance maintenance2 = MAINTENANCE_SERVICE.addMaintenance("Cleaning clothes ", 30.00);
        Maintenance maintenance3 = MAINTENANCE_SERVICE.addMaintenance("Food ordering ", 120.00);
        MAINTENANCE_DAO.getAll().forEach(System.out::println);

        GUEST_SERVICE.orderMaintenance(2L, 1L);
        GUEST_SERVICE.orderMaintenance(2L, 2L);
        GUEST_SERVICE.orderMaintenance(2L, 3L);

        System.out.println("Стоимость номера + услуги: " + GUEST_SERVICE.roomBill(guest2));

        System.out.println("---------Посмотреть 3-х последних постояльцев номера и даты пребывания-------------------");
        ROOM_SERVICE.showThreeLastGuestsRooms(5L);

        System.out.println("---------Посмотреть список услуг постояльца и их цену (сортировать по цене, по имени)----");
        GUEST_SERVICE.getAllMaintenancesGuest(2L, new MaintPriceComparator()).forEach(System.out::println);
        System.out.println("*******************************************");
        GUEST_SERVICE.getAllMaintenancesGuest(2L, new MaintNameComparator()).forEach(System.out::println);

        System.out.println("---------Цены услуг и номеров (сортировать по цене, по разделу)--------------------------");
        ABSTRACT_SERVICE.PricesForMaintenancesAndRooms().forEach(System.out::println);
        System.out.println("*******************************************");
        ABSTRACT_SERVICE.PricesForMaintenancesAndRooms(new AbstrPiceComparator()).forEach(System.out::println);

        System.out.println("---------Посмотреть детали отдельного номера---------------------------------------------");
        ROOM_SERVICE.showRoomDetails(5l);
















//
    }
}
