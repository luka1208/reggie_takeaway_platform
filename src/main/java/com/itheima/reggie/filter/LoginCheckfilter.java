package com.itheima.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.itheima.reggie.common.BaseContext;
import com.itheima.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否完成登陆
 * Check if the user has completed the login
 */
@Slf4j
@WebFilter(filterName = "LoginCheckFilter",urlPatterns = "/*")
public class LoginCheckfilter implements Filter {
    //路径匹配器，支持通配符
    //Path matcher, supports wildcards
    public static final AntPathMatcher PATH_MATCHER =new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.获取本次请求的URI
        //1. Get the URI of the current request
        String requestURI = request.getRequestURI();

        log.info("拦截到请求:{}",requestURI);
        //定义不需要处理的请求
        //Define requests that do not need to be processed
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };

        //2.判断本次请求是否需要处理
        //2. Determine whether the current request needs to be processed
        boolean check = check(urls, requestURI);


        //3.如果不需要处理，则直接放行
        //3. If it does not need to be processed, it will be released directly

        if (check){
            log.info("本次请求{}，不需要处理",requestURI);
            filterChain.doFilter(request,response);
            return;
        }
        //4.判断登陆状态，如果已登陆，则直接放行
        //4. Check the login status. If logged in, go directly
        if (request.getSession().getAttribute("employee")!=null){
            log.info("用户已登陆,用户id为:{}",request.getSession().getAttribute("employee"));

            Long empId = (Long)request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);

            filterChain.doFilter(request,response);
            return;
        }

        log.info("用户未登录");
        //5.如果未登录，则返回未登录结果，通过输出流方式向客户端页面响应数据
        //5. If not logged in, return the result of not logged in,
        //   and respond to the client page through the output stream

        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;



    }

    //路径匹配，检查本次请求是否需要放行
    //Path matching, check if the current request needs to be released
    public boolean check(String[] urls,String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match==true){
                return true;
            }

        }
        return false;
    }

}
