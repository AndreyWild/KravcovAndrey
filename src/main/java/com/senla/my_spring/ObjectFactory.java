package com.senla.my_spring;

import com.senla.my_spring.configurations.ApplicationContext;
import com.senla.my_spring.configurations.interfaces.ObjectConfigurator;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ObjectFactory {

    private List<ObjectConfigurator> configurators = new ArrayList<>();
    private final ApplicationContext context;

    public ObjectFactory(ApplicationContext context) {
        this.context = context;

        for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            try {
                configurators.add(aClass.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                System.out.println("Something with constructor access!");
                e.printStackTrace();
            }
        }
    }

    public <T> T createObject(Class<T> implClass) {
        T t = create(implClass);
        configure(t);
        invokeInit(implClass, t);
        return t;
    }

    private <T> void invokeInit(Class<T> implClass, T t) {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                try {
                    method.invoke(t);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private <T> void configure(T initialized) {
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(initialized, context));
    }

    private <T> T create(Class<T> implClass) {
        T t = null;
        try {
            t = implClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.err.println("Something went wrong with creating an object from a class!");
            e.printStackTrace();
        }
        return t;
    }
}
