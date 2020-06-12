package com.rabbitmq.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.rabbitmq.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
