package com.rabbitmq.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.rabbitmq.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {


}
