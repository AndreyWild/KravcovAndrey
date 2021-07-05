package com.senla.my_spring.configurations;

import com.senla.my_spring.ObjectFactory;

import java.util.Map;

public class Application {

    // конструктор если есть интерфейсы с более чем одной имплиментацией
    public static ApplicationContext run(String packageToScan, Map<Class, Class> ifcToImplClass){
        // создаем объект config из String (путь к папке которую надо сканировать и Map с разными имплиментациями)
        JavaConfig config = new JavaConfig(packageToScan, ifcToImplClass);
        // создаем новый объект context добавляя в него config
        ApplicationContext context = new ApplicationContext(config);
        // создаем новый объект factory добавляя в него context
        ObjectFactory objectFactory = new ObjectFactory(context);
        // добавляем в context objectFactory
        context.setFactory(objectFactory);
        return context;
    }

    // конструктор если интерфейс только с одной имплиментацией
    public static ApplicationContext run(String packageToScan){
        // создаем объект config из String (путь к папке которую надо сканировать)
        JavaConfig config = new JavaConfig(packageToScan);
        // создаем новый объект context добавляя в него config
        ApplicationContext context = new ApplicationContext(config);
        // создаем новый объект factory добавляя в него context
        ObjectFactory objectFactory = new ObjectFactory(context);
        // добавляем в context objectFactory
        context.setFactory(objectFactory);
        return context;
    }
}
