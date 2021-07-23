package com.senla.my_spring.configurations;

import com.senla.my_spring.annotations.InjectProperty;
import com.senla.my_spring.configurations.interfaces.IObjectConfigurator;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class InjectPropertyAnnotationObjectConfigurator implements IObjectConfigurator {

    private static final Logger LOGGER = Logger.getLogger(InjectPropertyAnnotationObjectConfigurator.class.getName());

    Map<String, String> propertiesMap; // Map содержит значения из файла application.properties

    public InjectPropertyAnnotationObjectConfigurator() {
        String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
        Stream<String> lines = null;
        try {
            lines = new BufferedReader(new FileReader(path)).lines();
        } catch (FileNotFoundException e) {
            LOGGER.warn(e.getMessage(), e);
            System.err.println("File not found!");
        }
        propertiesMap = lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));
    }

    @Override
    public void configure(Object t, ApplicationContext context) {
        Class<?> implClass = t.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            InjectProperty annotation = field.getAnnotation(InjectProperty.class);
            if (annotation != null) {
                String value;
                if (annotation.value().isEmpty()) {
                    value = propertiesMap.get(field.getName());
                } else {
                    value = propertiesMap.get(annotation.value());
                }
                field.setAccessible(true);
                try {
                    field.set(t, value);
                } catch (IllegalAccessException e) {
                    LOGGER.warn(e.getMessage(), e);
                    System.err.println("Access to the field is closed!");
                }
            }
        }
    }
}
