package com.itheima.reggie.common;

/**
 * Custom service exceptions
 */
public class CustomException extends RuntimeException{
    public CustomException(String message) {
        super(message);
    }
}
