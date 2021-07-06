package com.senla.my_spring.configurations;


import com.senla.my_spring.configurations.interfaces.ProxyConfigurator;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HandlerProxyConfigurator implements ProxyConfigurator {
    @Override
    // конфигуратор принимает объект который возможно надо запроксировать
    public Object replaceWithProxyIfNeeded(Object t, Class implClass) {

        // если класс имеет аннотацию @Deprecated то возращаем Proxy
        if (implClass.isAnnotationPresent(Deprecated.class)) {

            // если у класса нету интерфейса
            if(implClass.getInterfaces().length == 0){
                return Enhancer.create(implClass, (net.sf.cglib.proxy.InvocationHandler) (proxy, method, args) -> getInvocationHandlerLogic(t, method, args));
            }

            return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(), (proxy, method, args) -> getInvocationHandlerLogic(t, method, args));
        } else {
            // иначе просто возвращаем объект ничего не спроксировав
            return t;
        }
    }

    private Object getInvocationHandlerLogic(Object t, Method method, Object[] args) throws IllegalAccessException, InvocationTargetException {
        return method.invoke(t, args);
    }
}
