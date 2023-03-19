package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.dto.SetmealDto;
import com.itheima.reggie.entity.Setmeal;
import org.springframework.transaction.annotation.Transactional;


public interface SetmealService extends IService<Setmeal> {
    @Transactional
    public void saveWithDish(SetmealDto setmealDto);

}
