package com.example.devblog.utils.error;

import lombok.Getter;

@Getter
public class DevBlogException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public DevBlogException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

}