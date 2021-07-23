package com.senla.my_spring.configurations;

import com.senla.my_spring.configurations.interfaces.IConfig;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReturnClassImplConfigurator implements IConfig {

    private Reflections scanner;
    private Map<Class, Class> ifc2ImplClass = new HashMap<>();

    public ReturnClassImplConfigurator(String packageToScan) {
        this.scanner = new Reflections(packageToScan);
    }

    @Override
    public <T> Class<? extends T> getImpClass(Class<T> ifc) {
        return ifc2ImplClass.computeIfAbsent(ifc, aClass -> {
            Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
            if (classes.size() != 1) {
                throw new RuntimeException("Interface has 0 implementations or more than one, update your config!");
            }
            return classes.iterator().next();
        });
    }

    @Override
    public Reflections getScanner() {
        return scanner;
    }
}
