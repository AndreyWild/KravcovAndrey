package com.senla.my_spring.configurations;

import com.senla.my_spring.annotations.Autowired;
import com.senla.my_spring.configurations.interfaces.ObjectConfigurator;

import java.lang.reflect.Field;

public class AutowiredAnnotationObjectConfigurator implements ObjectConfigurator {
    @Override
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                Object object = context.getObject(field.getType());
                try {
                    field.set(t, object);
                } catch (IllegalAccessException e) {
                    System.err.println("Access to the field is closed!");
                    e.printStackTrace();
                }
            }
        }
    }
}
