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
 * 套餐
 * Set meal
 */
@Data
public class Setmeal implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;


    //分类id
    //Category id
    private Long categoryId;


    //套餐名称
    //Set meal name
    private String name;


    //套餐价格
    //Set meal price
    private BigDecimal price;


    //状态 0:停用 1:启用
    //Status 0: disabled 1: enabled
    private Integer status;


    //编码
    //Code
    private String code;


    //描述信息
    //Description information
    private String description;


    //图片
    //Picture
    private String image;


    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    @TableField(fill = FieldFill.INSERT)
    private Long createUser;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;


    //是否删除
    //Is it deleted
    private Integer isDeleted;
}
