package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;


public interface DishService extends IService<Dish> {
    //新增菜品，同时插入菜品对应的口味数据，需要操作两张表：dish，dishFlavor
    public void saveWithFlavor(DishDto dishDto);
    //修改菜品，同时修改菜品对应的口味数据，需要操作两张表：dish，dishFlavor
    public DishDto getByIdWithFlavor(Long id);
    //更新菜品信息，同时更新菜品对应的口味数据，需要操作两张表：dish，dishFlavor
    public void updateWithFlavor(DishDto dishDto);
}
