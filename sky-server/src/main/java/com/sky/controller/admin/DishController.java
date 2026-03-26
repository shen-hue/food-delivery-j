package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.dto.DishPageQueryDTO;
import com.sky.service.impl.DishServiceImpl;
import com.sky.vo.DishVO;
import com.sky.entity.Dish;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关接口")
@Slf4j
public class DishController {
    private final DishServiceImpl dishServiceImpl;
    @Autowired
    private DishService dishService;

    DishController(DishServiceImpl dishServiceImpl) {
        this.dishServiceImpl = dishServiceImpl;
    }
    
    /**
     * 新增菜品
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增菜品")
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品 {}\",dishDTO");
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }
    
    @GetMapping("/page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("菜品分页查询");
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping
    @ApiOperation("批量删除菜品")
    public Result delete(@RequestParam List<Long> ids) {
        log.info("批量删除菜品: {}", ids);
        dishServiceImpl.deleteBatch(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<DishVO> getById(@PathVariable Long id) {
        log.info("根据id查询菜品: {}", id);
        DishVO dishVO = dishServiceImpl.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    @PutMapping
    public Result putMethodName(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品: {}", dishDTO);
        dishServiceImpl.updateWithFlavor(dishDTO);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Dish>> list(Long categoryId) {
        log.info("根据分类id查询菜品: {}", categoryId);
        List<Dish> list = dishServiceImpl.list(categoryId);
        return Result.success(list);
    }
    
    
}
