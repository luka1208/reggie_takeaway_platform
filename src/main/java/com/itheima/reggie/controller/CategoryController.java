package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.service.impl.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public R<String> save(@RequestBody Category category){
        log.info("category:{}",category);
        categoryService.save(category);
        return R.success("新增分类成功");//success to add category
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize){
        //分页构造器
        //Page constructor
        Page<Category> pageInfo = new Page<>(page, pageSize);
        //条件构造器
        //Conditional constructor
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //排序
        //Sort
        queryWrapper.orderByAsc(Category::getSort);
        //分页查询
        //Page query
        categoryService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 根据id删除分类
     * Delete category by id
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long ids){
        log.info("删除分类,id:{}",ids);//Delete category
        categoryService.remove(ids);
        return R.success("分类信息删除成功 ");//Category information deleted successfully
    }

    @PutMapping
    public R<String> update(@RequestBody Category category){
        log.info("修改分类信息,category:{}",category);
        categoryService.updateById(category);
        return R.success("分类信息修改成功");//Category information modified successfully
    }

    /**
     * 根据条件查询分类数据
     * Query category data according to the criteria
     * Query classified data according to the criteria
     * @param category
     * @return
     */
    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        //条件构造器
        //Conditional constructor
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        //Add conditions
        queryWrapper.eq(category.getType() != null,Category::getType,category.getType());
        //添加排序条件
        //Add sort conditions
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        List<Category> list = categoryService.list(queryWrapper);

        return R.success(list);
    }

}
