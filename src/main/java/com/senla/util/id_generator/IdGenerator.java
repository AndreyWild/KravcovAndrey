package com.senla.util.id_generator;

import com.senla.util.id_generator.api.IGuestIdGenerator;
import com.senla.util.id_generator.api.IMaintenanceIdGenerator;
import com.senla.util.id_generator.api.IRoomIdGenerator;

import java.io.File;

public class IdGenerator implements IGuestIdGenerator, IRoomIdGenerator, IMaintenanceIdGenerator {

    private static final File GUEST_ID_FILE = new File("src/main/java/com/senla/util/id_generator/files/guest_id.txt");
    private static final File ROOM_ID_FILE = new File("src/main/java/com/senla/util/id_generator/files/room_id.txt");
    private static final File MAINTENANCE_ID_FILE = new File("src/main/java/com/senla/util/id_generator/files/maintenance_id.txt");


    private static Long guestId = IDControlInFile.receiveId(GUEST_ID_FILE);
    private static Long roomId = IDControlInFile.receiveId(ROOM_ID_FILE);
    private static Long maintenanceId = IDControlInFile.receiveId(MAINTENANCE_ID_FILE);

    public Long generateGuestId() {
        IDControlInFile.saveId(guestId + 1, GUEST_ID_FILE);
        return guestId++;
    }

    public Long generateRoomId() {
        IDControlInFile.saveId(roomId + 1, ROOM_ID_FILE);
        return roomId++;
    }

    public Long generateMaintenanceId() {
        IDControlInFile.saveId(maintenanceId + 1, MAINTENANCE_ID_FILE);
        return maintenanceId++;
    }
}
