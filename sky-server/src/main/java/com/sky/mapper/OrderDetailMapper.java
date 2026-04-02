package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sky.entity.OrderDetail;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    void insertBatch(List<OrderDetail> orderDetailList);
}
