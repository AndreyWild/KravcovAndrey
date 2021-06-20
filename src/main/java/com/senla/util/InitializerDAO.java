package com.senla.util;

import com.senla.api.dao.IGuestDao;
import com.senla.api.dao.IMaintenanceDao;
import com.senla.api.dao.IRoomDao;
import com.senla.dao.GuestDao;
import com.senla.dao.MaintenanceDao;
import com.senla.dao.RoomDao;

public class InitializerDAO {

    public static final IGuestDao GUEST_DAO = new GuestDao();
    public static final IRoomDao ROOM_DAO = new RoomDao();
    public static final IMaintenanceDao MAINTENANCE_DAO = new MaintenanceDao();
}
