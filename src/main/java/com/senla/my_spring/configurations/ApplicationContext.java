package com.senla.my_spring.configurations;

import com.senla.my_spring.ObjectFactory;
import com.senla.my_spring.annotations.Singleton;
import com.senla.my_spring.configurations.interfaces.Config;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    @Setter
    private ObjectFactory factory;
    private Map<Class, Object> cache = new ConcurrentHashMap<>();
    @Getter
    private Config config;

    public ApplicationContext(Config config) {
        this.config = config;
    }

    // дай мне объект типа из параметра
    public <T> T getObject(Class<T> type) {
        Class<? extends T> implClass = type;
        // если cache содержит такой тип
        if(cache.containsKey(type)){
            // то овзращаем для него объект из этого cache по типу
            return (T) cache.get(type);
        }
        // если класс является интерфейсом
        if (type.isInterface()) {
            // то implClass = классу который имплиментирует этот объект
            implClass = config.getImplClass(type);
        }
        // t = фабрика создай объект типа type
        T t = factory.crateObject(implClass);

        // если у объекта есть аннотация Singleton
        if(implClass.isAnnotationPresent(Singleton.class)){
            // тогда добавляем этот объект в cache
            cache.put(type, t);
        }
        return t;
    }
}
