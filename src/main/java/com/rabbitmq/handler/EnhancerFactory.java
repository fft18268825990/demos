package com.rabbitmq.handler;

import org.springframework.cglib.proxy.Enhancer;

public class EnhancerFactory {
    public static GclibTestVo getInstance(GclibProxy proxy){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(GclibTestVo.class);
        enhancer.setCallback(proxy);
        GclibTestVo gclibTestVo = (GclibTestVo)enhancer.create();
        return gclibTestVo;
    }
}
