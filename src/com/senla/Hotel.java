package com.senla;

import com.senla.api.service.IMultipleEntitiesService;
import com.senla.api.service.IGuestService;
import com.senla.api.service.IMaintenanceService;
import com.senla.api.service.IRoomService;
import com.senla.model.*;
import com.senla.service.MultipleEntitiesService;
import com.senla.service.GuestService;
import com.senla.service.MaintenanceService;
import com.senla.service.RoomService;
import com.senla.util.sorter.abstr.AbstrPiceComparator;
import com.senla.util.sorter.maintenance.MaintNameComparator;
import com.senla.util.sorter.maintenance.MaintenancePriceComparator;
import com.senla.util.sorter.rooms.RoomsCapacityComparator;
import com.senla.util.sorter.guests.GuestNameComparator;
import com.senla.util.sorter.rooms.RoomsPriceComparator;
import com.senla.util.sorter.rooms.RoomsStarsComparator;
import com.senla.util.DatePeriodGenerator;

import java.time.LocalDate;

public class Hotel {

    /* Designed for health check and code validation. Replacement of MySQL Workbench. */
//    private static IGuestDao guestDao = new GuestDao();
//    private static IRoomDao roomDao = new RoomDao();
//    private static IMaintenanceDao maintenanceDao = new MaintenanceDao();

//    private static IGuestService guestService = new GuestService(guestDao, maintenanceDao);
//    private static IRoomService roomService = new RoomService(roomDao, guestDao, maintenanceDao);
//    private static IMaintenanceService maintenanceService = new MaintenanceService(maintenanceDao);
//    private static IMultipleEntitiesService multipleEntitiesService = new MultipleEntitiesService(guestDao, roomDao, maintenanceDao);

    private static IGuestService guestService = new GuestService();
    private static IRoomService roomService = new RoomService();
    private static IMaintenanceService maintenanceService = new MaintenanceService();
    private static IMultipleEntitiesService multipleEntitiesService = new MultipleEntitiesService();

    public static void main(String[] args) {
        System.out.println("------Создаю 10-х гостей------------------------------------------------------------------");
        guestService.addGuest("Eddard Stark", 50);
        guestService.addGuest("Catelyn Stark", 46);
        guestService.addGuest("Robb Stark", 26);
        guestService.addGuest("Sansa Stark", 46);
        guestService.addGuest("Arya Stark", 17);
        guestService.addGuest("Bran Stark", 16);
        guestService.addGuest("Rickon Stark", 10);
        guestService.addGuest("Jon Snow", 25);
        guestService.addGuest("Benjen Stark", 64);
        guestService.addGuest("Lyanna Stark", 52);
        guestService.getAll().forEach(System.out::println);

        System.out.println("--------Создаю номера--------------------------------------------------------------------");
        roomService.addRoom(358, 1, 500.0, 5);
        roomService.addRoom(12, 2, 400.0, 4);
        roomService.addRoom(5, 3, 350.0, 3);
        roomService.addRoom(82, 4, 300.0, 2);
        roomService.addRoom(108, 5, 250.0, 1);
        roomService.getAll().forEach(System.out::println);

        System.out.println("--------Заселяю 5-х гостей в номер и прверяю их статус-----------------------------------");
        System.out.println(guestService.getById(1L).getGuestStatus());
        System.out.println(guestService.getById(2L).getGuestStatus());
        roomService.checkIn(1L, 5L, "2021/07/21");
        roomService.checkIn(2L, 5L, "2021/09/05");
        roomService.checkIn(3L, 5L, "2021/10/02");
        roomService.checkIn(4L, 5L, "2021/08/15");
        roomService.checkIn(5L, 5L, "2021/12/31");

        System.out.println(roomService.getById(5L));
        System.out.println(guestService.getById(1L));
        System.out.println(guestService.getById(1L).getGuestStatus());
        System.out.println(guestService.getById(2L).getGuestStatus());
        System.out.println(guestService.getById(3L).getGuestStatus());
        System.out.println(guestService.getById(4L).getGuestStatus());
        System.out.println(guestService.getById(5L).getGuestStatus());

        System.out.println("---------Удаляю гостя 1 из комнаты и проверяю его статус---------------------------------");
        roomService.evictGuest(1L);
        System.out.println(roomService.getById(5L));
        System.out.println(guestService.getById(1L).getGuestStatus());

        System.out.println("---------Проверяем клиентов--------------------------------------------------------------");
        System.out.println(guestService.getById(1L));
        System.out.println(guestService.getById(2L));

        System.out.println("---------Меняем статус комнаты-----------------------------------------------------------");
        roomService.getById(1L).setStatus(RoomStatus.ON_REPAIR);
        System.out.println(roomService.getById(1L));
        roomService.getById(1L).setStatus(RoomStatus.CLOSED);
        System.out.println(roomService.getById(3L));

        System.out.println("---------Создаю услугу-------------------------------------------------------------------");
        maintenanceService.addMaintenance("Cleaning", 50.0);
        System.out.println(maintenanceService.getById(1L));

        System.out.println("---------Сортировка комнат по цене-------------------------------------------------------");
        roomService.getAll(new RoomsPriceComparator()).forEach(System.out::println);

        System.out.println("---------Сортировка комнат по вместимости------------------------------------------------");
        roomService.getAll(new RoomsCapacityComparator()).forEach(System.out::println);

        System.out.println("---------Сортировка комнат по количесту звезд--------------------------------------------");
        roomService.getAll(new RoomsStarsComparator()).forEach(System.out::println);

        System.out.println("---------Сортировка свободных комнат по цене---------------------------------------------");
        roomService.getOpenRooms(new RoomsPriceComparator()).forEach(System.out::println);

        System.out.println("---------Сортировка свободных комнат по вместимости--------------------------------------");
        roomService.getOpenRooms(new RoomsCapacityComparator()).forEach(System.out::println);

        System.out.println("---------Сортировка свободных комнат по количесту звезд----------------------------------");
        roomService.getOpenRooms(new RoomsStarsComparator()).forEach(System.out::println);

        System.out.println("---------Сортировка гостей по алфавиту---------------------------------------------------");
        guestService.getAll(new GuestNameComparator()).forEach(System.out::println);

        System.out.println("---------Список номеров которые будут свободны по определенной дате----------------------");
        LocalDate date = LocalDate.of(2021, 05, 25);
        LocalDate date2 = LocalDate.of(2021, 12, 31);

        roomService.getById(1L).setBusyDates(DatePeriodGenerator.toDateList("2021/05/01", "2021/05/30"));
        roomService.getById(2L).setBusyDates(DatePeriodGenerator.toDateList("2021/05/01", "2021/05/20"));
        roomService.getById(3L).setBusyDates(DatePeriodGenerator.toDateList("2021/05/01", "2021/05/30"));
        roomService.getById(4L).setBusyDates(DatePeriodGenerator.toDateList("2021/05/01", "2021/05/20"));
        roomService.getById(5L).setBusyDates(DatePeriodGenerator.toDateList("2021/05/01", "2021/05/30"));

        roomService.getAvailableRoomsForDate(date).forEach(System.out::println);

        System.out.println("---------Создаю еще 2 услуги, добавляю их гостю и вывожу счет за номер-------------------");
        Maintenance maintenance2 = maintenanceService.addMaintenance("Cleaning clothes ", 30.00);
        Maintenance maintenance3 = maintenanceService.addMaintenance("Food ordering ", 120.00);
        maintenanceService.getAll().forEach(System.out::println);

        guestService.orderMaintenance(2L, 1L);
        guestService.orderMaintenance(2L, 2L);
        guestService.orderMaintenance(2L, 3L);

        System.out.println("Стоимость номера + услуги: " + guestService.getInvoiceForRoomAndMaintenances(2L));

        System.out.println("---------Посмотреть 3-х последних постояльцев номера и даты пребывания-------------------");
        roomService.showThreeLastGuests(5L);

        System.out.println("---------Посмотреть список услуг постояльца и их цену (сортировать по цене, по имени)----");
        guestService.getAllMaintenancesGuest(2L, new MaintenancePriceComparator()).forEach(System.out::println);
        System.out.println("*******************************************");
        guestService.getAllMaintenancesGuest(2L, new MaintNameComparator()).forEach(System.out::println);

        System.out.println("---------Цены услуг и номеров (сортировать по цене, по разделу)--------------------------");
        multipleEntitiesService.getPricesForMaintenancesAndRoom().forEach(System.out::println);
        System.out.println("*******************************************");
        multipleEntitiesService.getPricesForMaintenancesAndRoom(new AbstrPiceComparator()).forEach(System.out::println);

        System.out.println("---------Посмотреть детали отдельного номера---------------------------------------------");
        roomService.showRoomDetails(5l);
    }
}
