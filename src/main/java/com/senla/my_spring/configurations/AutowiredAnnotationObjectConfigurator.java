package com.senla.my_spring.configurations;

import com.senla.my_spring.annotations.Autowired;
import com.senla.my_spring.configurations.interfaces.IObjectConfigurator;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;

public class AutowiredAnnotationObjectConfigurator implements IObjectConfigurator {

    private static final Logger LOGGER = Logger.getLogger(AutowiredAnnotationObjectConfigurator.class.getName());
    @Override
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                Object object = context.getObject(field.getType());
                try {
                    field.set(t, object);
                } catch (IllegalAccessException e) {
                    LOGGER.warn(e.getMessage(), e);
                    System.err.println("Access to the field is closed!");
                }
            }
        }
    }
}
