package com.senla.my_spring.configurations;


import com.senla.my_spring.annotations.InjectByType;
import com.senla.my_spring.configurations.interfaces.ObjectConfigurator;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class InjectByTypeAnnotationObjectConfigurator implements ObjectConfigurator {
    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        // берем все поля и итерируем их
        for (Field field : t.getClass().getDeclaredFields()) {
            // если поле аннотировано аннотацией InjectByType
            if(field.isAnnotationPresent(InjectByType.class)){
                // тогда мы хотим просетить это поле по типу поля
                // открываем доступ к приватному полю
                field.setAccessible(true);
                /*
                // инициализиреум фабрику объектов, создай объект который будет соответствовать типу поля
                Object object = ObjectFactory.getInstance().crateObject(field.getType());
                */

                Object object = context.getObject(field.getType());

                // проиниицализируй поле в объекте t полученныйм из фабрики объектом
                field.set(t, object);
            }
        }
    }
}
