package com.rabbitmq.controller;

import com.rabbitmq.entity.User;
import com.rabbitmq.service.UserService;
import com.rabbitmq.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/selectById/{id}")
    public User selectById(@PathVariable("id") Integer id){
        return userService.selectById(id);
    }

    @GetMapping(value = "/updateById")
    public Integer updateById(User user){
        return userService.updateById(user);
    }

    @GetMapping(value = "/deleteById/{id}")
    public Integer deleteById(@PathVariable("id") Integer id){
        return userService.deleteById(id);
    }

    @PostMapping(value = "/insert")
    public Integer insert(User user){
        userService.insert(user);
        return user.getUserId();
    }

    @PostMapping(value = "/login")
    public Map<String,Object> login(User user){
        Map<String,Object> map = new HashMap<String,Object>();
        User loginUser = userService.login(user);
        if(loginUser!=null){
            String token = JWTUtil.sign(loginUser.getUsername(),loginUser.getPassword());
            if(token!=null){
                map.put("code",200);
                map.put("message","认证成功");
                map.put("token",token);
                return map;
            }else{
                map.put("code",403);
                map.put("message","token生成错误，认证失败");
                return map;
            }
        }else{
            map.put("code",403);
            map.put("message","用户名密码错误，认证失败");
            return map;
        }
    }
}
