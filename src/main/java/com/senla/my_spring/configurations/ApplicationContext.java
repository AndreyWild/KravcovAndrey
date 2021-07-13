package com.senla.my_spring.configurations;

import com.senla.my_spring.ObjectFactory;
import com.senla.my_spring.annotations.Singleton;
import com.senla.my_spring.configurations.interfaces.IConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {


    private ObjectFactory factory;
    private Map<Class, Object> cache = new ConcurrentHashMap<>();
    private IConfig config;

    public ApplicationContext(IConfig config) {
        this.config = config;
    }

    public <T> T getObject(Class<T> type) {
        if (cache.containsKey(type)) {
            return (T) cache.get(type);
        }

        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImpClass(type);
        }
        T t = factory.createObject(implClass);
        if (implClass.isAnnotationPresent(Singleton.class)) {
            cache.put(type, t);
        }
        return t;
    }

    public void setFactory(ObjectFactory factory) {
        this.factory = factory;
    }

    public IConfig getConfig() {
        return config;
    }
}
