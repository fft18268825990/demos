package com.rabbitmq;

import com.rabbitmq.handler.MyInvocationHandler;
import com.rabbitmq.service.ProxyService;
import com.rabbitmq.service.impl.ProxyServiceImpl;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args){
        ProxyService proxyService = new ProxyServiceImpl();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(proxyService);
        ProxyService proxyServiceProxy = (ProxyService)Proxy.newProxyInstance(proxyService.getClass().getClassLoader(),
                proxyService.getClass().getInterfaces(),myInvocationHandler);
        proxyServiceProxy.hello();
    }
}
