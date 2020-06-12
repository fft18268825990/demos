package com.rabbitmq;

import com.rabbitmq.handler.EnhancerFactory;
import com.rabbitmq.handler.GclibProxy;
import com.rabbitmq.handler.GclibTestVo;

public class GclibTest {
    public static void main(String[] args){
        GclibProxy gclibProxy = new GclibProxy();
        GclibTestVo gclibTestVo = EnhancerFactory.getInstance(gclibProxy);
        gclibTestVo.hello();
    }
}
