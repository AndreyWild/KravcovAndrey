package com.senla.my_spring.configurations;

import com.senla.my_spring.ObjectFactory;

public class Application {
    public static ApplicationContext run(String packageToScan) {
        JavaObjectsConfigurator config = new JavaObjectsConfigurator(packageToScan);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setFactory(objectFactory);
        return context;
    }
}
