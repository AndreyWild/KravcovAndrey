package com.senla.my_spring.configurations;

import com.senla.my_spring.annotations.InjectProperty;
import com.senla.my_spring.configurations.interfaces.ObjectConfigurator;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class InjectPropertyAnnotationObjectConfigurator implements ObjectConfigurator {

    private Map<String, String> propertiesMap;

    @SneakyThrows
    public InjectPropertyAnnotationObjectConfigurator() {
        // получаем путь к файлу
        String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
        // получили стрим из считанных строк из файла
        Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
        // создали map из стрима
        propertiesMap = lines
                // поулчили массив мз String по разделителю "="
                .map(line -> line.split("="))
                // собрали map из массива
                .collect(toMap(arr -> arr[0], arr -> arr[1]));
    }

    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        Class<?> implClass = t.getClass();
        // пройдемся по полям объекта
        for (Field field : implClass.getDeclaredFields()) {
            // поулчаем аннотацию объекта
            InjectProperty annotation = field.getAnnotation(InjectProperty.class);

            // если она не равна null
            if (annotation != null) {
                String value;
                // если в аннотации нету value
                if (annotation.value().isEmpty()) {
                    // идем про propertiesMap и вытягиваем из него имя поля по названию филда
                    value = propertiesMap.get(field.getName());
                } else {
                    // иначе взять value из значения в аннотации
                    value = propertiesMap.get(annotation.value());
                }
                // открываем доступ к приватному полю
                field.setAccessible(true);
                // объекту t присваиваем полученное ранее значение
                field.set(t, value);
            }
        }

    }
}
