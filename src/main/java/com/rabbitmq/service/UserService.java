package com.rabbitmq.service;

import com.rabbitmq.entity.User;


public interface UserService {

    public User selectById(Integer id);

    public Integer updateById(User user);

    public Integer deleteById(Integer id);

    public Integer insert(User user);

    User login(User user);

    String getPassword(String username);
}
