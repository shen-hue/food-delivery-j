package com.sky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sky.entity.SetmealDish;

@Mapper
public interface SetmealDishMapper {
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);

    void insertBatch(List<SetmealDish> setmealDishes);
}