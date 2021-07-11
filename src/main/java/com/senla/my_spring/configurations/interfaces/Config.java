package com.senla.my_spring.configurations.interfaces;

import org.reflections.Reflections;

public interface Config {
    <T> Class<? extends T> getImpClass(Class<T> ifc);

    Reflections getScanner();
}
