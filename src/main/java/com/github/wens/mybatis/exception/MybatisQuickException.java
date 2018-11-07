package com.github.wens.mybatis.exception;

/**
 * @author wens
 * @Date 2018-10-10
 */
public class MybatisQuickException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MybatisQuickException(String message) {
        super(message);
    }

    public MybatisQuickException(Throwable throwable) {
        super(throwable);
    }

    public MybatisQuickException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
