package com.rabbitmq;

import com.rabbitmq.entity.User;

import java.lang.reflect.Method;

public class TEST {
    public static void main(String[] args) throws Exception{
        Class userClass = User.class;
        Object user = userClass.newInstance();
        Method m = userClass.getDeclaredMethod("setUsername",String.class);
        m.invoke(user,"fangfeiting");
        System.out.println(((User)user).getUsername());
    }
}
