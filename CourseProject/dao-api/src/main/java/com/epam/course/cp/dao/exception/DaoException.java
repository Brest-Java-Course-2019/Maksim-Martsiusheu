package com.epam.course.cp.dao.exception;

public class DaoException extends RuntimeException {

    public DaoException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
