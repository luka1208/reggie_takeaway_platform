package com.itheima.reggie.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 分类
 * category
 */
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;


    //类型 1 菜品分类 2 套餐分类
    //Type 1 dish category 2 set meal category
    private Integer type;


    //分类名称
    //Category name
    private String name;


    //顺序
    //Order
    private Integer sort;


    //创建时间
    //Create time
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    //更新时间
    //Update time
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    //创建人
    //Creator
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;


    //修改人
    //Modifier
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;


    //是否删除
    //Whether to delete
    //private Integer isDeleted;

}
