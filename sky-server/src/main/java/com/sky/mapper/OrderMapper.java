package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sky.entity.Orders;


@Mapper
public interface OrderMapper {
    void insert(Orders orders);

}
