package com.senla.my_spring.configurations;

;
import com.senla.my_spring.configurations.interfaces.Config;
import lombok.Getter;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JavaConfig implements Config {

    @Getter
    private Reflections scanner;
    private Map<Class, Class> ifcToImplClass;

    public JavaConfig(String packageToScan, Map<Class, Class> ifcToImplClass) {
        this.ifcToImplClass = ifcToImplClass;
        this.scanner = new Reflections(packageToScan);
    }

    public JavaConfig(String packageToScan) {
        this.ifcToImplClass = new HashMap<>();
        this.scanner = new Reflections(packageToScan);
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        // принимает ключь, если ключь существует - возвращает значение, если ключа нету,
        // запускает лямбду, лямбда возвращает значение, значение засетится в map на будущее и вернется
        return ifcToImplClass.computeIfAbsent(ifc, aClass -> {
            // дай список классов которые реализуют интерфейс
            Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
            // если у интерфейса больше чем одна имплиментация или вообще нету
            if (classes.size() != 1) {
                // то выкидываем исключение
                throw new RuntimeException(ifc + " has 0 or more than one impl please update your config");
            }
            // берем первое значение из set
            return classes.iterator().next();
        });
    }
}
