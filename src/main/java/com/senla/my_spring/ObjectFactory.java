package com.senla.my_spring;

import com.senla.my_spring.configurations.ApplicationContext;
import com.senla.my_spring.configurations.interfaces.IObjectConfigurator;
import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ObjectFactory {

    private static final Logger LOGGER = Logger.getLogger(ObjectFactory.class.getName());

    private List<IObjectConfigurator> configurators = new ArrayList<>();
    private final ApplicationContext context;

    public ObjectFactory(ApplicationContext context) {
        this.context = context;

        for (Class<? extends IObjectConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(IObjectConfigurator.class)) {
            try {
                configurators.add(aClass.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                LOGGER.warn(e.getMessage(), e);
                System.out.println("Something with constructor access!");
                e.printStackTrace();
            }
        }
    }

    public <T> T createObject(Class<T> implClass) {
        T t = create(implClass);
        configure(t);
        return t;
    }

    private <T> void configure(T initialized) {
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(initialized, context));
    }

    private <T> T create(Class<T> implClass) {
        T t = null;
        try {
            Constructor<T> constructor = (Constructor<T>) implClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            t = constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.warn(e.getMessage(), e);
            System.err.println("Something went wrong with creating an object from a class!");
            e.printStackTrace();
        }
        return t;
    }
}
