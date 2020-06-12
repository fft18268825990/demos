package com.rabbitmq.handler;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class GclibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object target, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("==========   do something before do method   ==========");
        methodProxy.invokeSuper(target,objects);
        System.out.println("==========   do something after do method   ==========");
        return null;
    }
}
