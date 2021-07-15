package com.senla.facade;

import com.senla.my_spring.configurations.Application;
import com.senla.my_spring.configurations.ApplicationContext;

public class HotelFacadeDispenser {

    private static HotelFacade facade;

    public static HotelFacade getHotelFacade(){
        ApplicationContext context = Application.run("com.senla");
        facade = context.getObject(HotelFacade.class);
        return facade;
    }
}
