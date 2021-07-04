package com.senla.my_spring.configurations.interfaces;

import org.reflections.Reflections;

public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> ifc);

    Reflections getScanner();
}
