package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.result.PageResult;
import com.sky.dto.DishPageQueryDTO;
import com.sky.vo.DishVO;
import com.sky.entity.Dish;

import java.util.List;

public interface DishService {
    public void saveWithFlavor(DishDTO dishDTO);
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);
    public void deleteBatch(List<Long> ids);
    public DishVO getByIdWithFlavor(Long id);
    public void updateWithFlavor(DishDTO dishDTO);
    public List<Dish> list(Long categoryId);
    public void startOrStop(Integer status, Long id);
        /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);
}
