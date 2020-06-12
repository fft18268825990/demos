package com.rabbitmq.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

@TableName(value = "r_stock")
@Data
public class Stock implements Serializable {

    private static final long serialVersionUID = 8750639181544596096L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer stock;

}
