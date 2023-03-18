package com.itheima.reggie.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 菜品
 dish
 */
@Data
public class Dish implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;


    //菜品名称
    //Dish name
    private String name;


    //菜品分类id
    //Dish category id
    private Long categoryId;


    //菜品价格
    //Dish price
    private BigDecimal price;


    //商品码
    //Product code
    private String code;


    //图片
    //Picture
    private String image;


    //描述信息
    //Description information
    private String description;


    //0 停售 1 起售
    //0 stop selling 1 start selling
    private Integer status;


    //顺序
    //Order
    private Integer sort;


    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    @TableField(fill = FieldFill.INSERT)
    private Long createUser;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;


    //是否删除
    //Whether to delete
    private Integer isDeleted;

}
