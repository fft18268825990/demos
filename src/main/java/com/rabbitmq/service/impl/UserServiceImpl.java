package com.rabbitmq.service.impl;

import com.rabbitmq.entity.User;
import com.rabbitmq.mapper.UserMapper;
import com.rabbitmq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    @Cacheable(cacheNames = {"user"},unless = "#result == null")
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    @CachePut(cacheNames = {"user"}, key = "#user.user_id") //注意：修改缓存时，key要和放入的相同。getEmp方法缓存默认以id，该方法也应为id，否则不能成功更新缓存，而是以对象为id，将该方法的结果存入缓存。
    public Integer updateById(User user) {
        return userMapper.updateById(user);
    }

    @Override
    @CacheEvict(cacheNames = {"user"})
    public Integer deleteById(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    @CachePut(cacheNames = {"user"}, key = "#user.userId")
    public Integer insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User login(User user) {
        return userMapper.selectOne(user);
    }

    @Override
    public String getPassword(String username) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("username",username);
        List<User> userList = userMapper.selectByMap(map);
        if(!userList.isEmpty() && userList.size()>0){
            return userList.get(0).getPassword();
        }
        return null;
    }
}
