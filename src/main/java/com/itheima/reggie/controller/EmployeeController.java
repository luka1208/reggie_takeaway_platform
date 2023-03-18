package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.service.impl.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){

    //1.将页面提交的密码password进行md5加密处理
        //1. Encrypt the password submitted on the page with md5
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

     //2.根据页面提交的用户名username查询数据库
        //2. Query the database according to the username submitted on the page
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

     //3.如果没有查询到则返回登录失败结果
        //3. If no query is found, return the login failure result
        if (emp==null){
            return R.error("successfully login");
        }
     //4.密码比对，如果不一致则返回登录失败结果
        //4. Password comparison, if not consistent, return login failure result
        if (!emp.getPassword().equals(password)){
            return R.error("login failure");
        }
     //5.查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        //5. Check the status of the employee.
        //   If it is disabled, return the result that the employee is disabled
        if(emp.getStatus()==0){
            return R.error("the account of employee is disabled");
        }

     //6.登陆成功，将员工id存入Session并返回登录成功结果
        //6. Login successfully, store the employee id in Session and return the login success result
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }

    /**
     * 员工退出
     * employee logout
     * @param request
     * @return
     */
    @PostMapping("logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");//successfully logout
    }

    /**
     * 新增员工信息
     */
    @PostMapping
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee){
        log.info("新增员工，员工信息：{}",employee.toString());//new employee, employee information
        //设置初始密码，并用md5加密
        //Set the initial password and encrypt it with md5
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
          //获取用户Id
          //Get user id
//        Long empId = (Long)request.getSession().getAttribute("employee");
//        employee.setCreateUser(empId);
//        employee.setUpdateUser(empId);

        employeeService.save(employee);

        return R.success("新增员工成功");//successfully add new employee
    }

    /**
     *  员工信息分页查询
     *  employee information paging query
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        log.info("page = {}, pageSize = {} ,name = {}",page,pageSize,name);
       
        //构造分页查询器
        //Construct paging query
        Page pageInfo = new Page(page, pageSize);

        //构造条件构造器
        //Construct condition constructor
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //过滤条件
        //Filter condition
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        //排序条件
        //Sort condition
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        //执行查询
        //Execute query
        employeeService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }
    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
        log.info(employee.toString());
//        Long empId = (Long) request.getSession().getAttribute("employee");
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setUpdateUser(empId);

        employeeService.updateById(employee);
        return R.success("修改成功");//successfully modify
    }
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        log.info("根据id查询员工信息");//Query employee information by id
        Employee employee = employeeService.getById(id);
        if (employee!=null){
            return R.success(employee);
        }
        return R.error("没有查询到对应员工信息");//No corresponding employee information was queried

    }


}
