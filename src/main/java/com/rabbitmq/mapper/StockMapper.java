package com.rabbitmq.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.rabbitmq.entity.Stock;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockMapper extends BaseMapper<Stock> {

}
