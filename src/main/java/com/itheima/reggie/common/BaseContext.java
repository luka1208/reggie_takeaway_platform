package com.itheima.reggie.common;

/**
 * Based on ThreadLocal encapsulated tool class, user saves and gets the current logged-in user id
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public  static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }

}
