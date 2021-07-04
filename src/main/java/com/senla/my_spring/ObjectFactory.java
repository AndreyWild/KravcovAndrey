package com.senla.my_spring;

import com.senla.my_spring.configurations.ApplicationContext;
import com.senla.my_spring.configurations.interfaces.ObjectConfigurator;
import com.senla.my_spring.configurations.interfaces.ProxyConfigurator;
import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class ObjectFactory {

    private final ApplicationContext context;
    private List<ObjectConfigurator> configurators = new ArrayList<>();
    private List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;

        // класс реализации ObjectConfigurator         : контекст, дай конфиг, дай сканнер(рефлексию), дай все реализации ObjectConfigurator
        for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            // список ObjectConfigurator добавить(класс реализации. создай объект  )
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }

        // класс реализации ProxyConfigurator         : контекст, дай конфиг, дай сканнер(рефлексию), дай все реализации ProxyConfigurator
        for (Class<? extends ProxyConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ProxyConfigurator.class)) {
            // список ProxyConfigurator добавить(класс реализации. создай объект  )
            proxyConfigurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T crateObject(Class<T> implClass) {

        // создаем объект
        T t = create(implClass);

        // конфигураторы настраивают объект
        configureIfNeeded(t);

        // метод запускает в объекте метод с аннотацией @PostConstruct
        invokeInit(implClass, t);

        t = wrapWithProxyIfNeeded(implClass, t);

        return t;
    }

    // метод возвращает прокси вместо объекта если это надо
    private <T> T wrapWithProxyIfNeeded(Class<T> implClass, T t) {
        for (ProxyConfigurator proxyConfigurator : proxyConfigurators) {
            t = (T) proxyConfigurator.replaceWithProxyIfNeeded(t, implClass);
        }
        return t;
    }

    // метод запускает в объекте метод с аннотацией @PostConstruct
    private <T> void invokeInit(Class<T> implClass, T t) throws
            IllegalAccessException, InvocationTargetException {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(t);
            }
        }
    }

    // метод настраивает объект если надо
    private <T> void configureIfNeeded(T t) {
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));
    }

    // метод создает объект
    private <T> T create(Class<T> implClass) throws
            InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        T t = implClass.getDeclaredConstructor().newInstance();
        return t;
    }
}
