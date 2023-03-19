package com.itheima.reggie.controller;

import com.itheima.reggie.service.impl.SetmealDishService;
import com.itheima.reggie.service.impl.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealDishController {

    @Autowired
    private SetmealDishService setmealDishService;

    @Autowired
    private SetmealService setmealService;




}
