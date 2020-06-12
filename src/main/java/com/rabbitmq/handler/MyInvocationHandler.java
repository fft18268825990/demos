package com.rabbitmq.handler;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    private Object target;
    public MyInvocationHandler(Object target){
        super();
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("==========   do something before do method   ==========");
        Object result = method.invoke(target,args);
        System.out.println("==========   do something after do method   ==========");
        return result;
    }
}
