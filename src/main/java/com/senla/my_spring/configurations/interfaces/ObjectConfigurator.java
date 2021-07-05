package com.senla.my_spring.configurations.interfaces;

import com.senla.my_spring.configurations.ApplicationContext;

public interface ObjectConfigurator {
    void configure(Object t, ApplicationContext context);
}
