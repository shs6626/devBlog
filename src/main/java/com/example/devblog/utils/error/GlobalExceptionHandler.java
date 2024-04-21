package com.example.devblog.utils.error;

import com.example.devblog.utils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();
        log.error("[유효성 검사] error: {}", errorMessage);
        return ApiResult.error(errorMessage);
    }

    @ExceptionHandler(RuntimeException.class)
    public ApiResult<Void> runtimeExceptionHandler(RuntimeException e) {
        String errorMessage = e.getMessage();
        log.error("[RuntimeException] error: {}", errorMessage);

        return ApiResult.error(errorMessage);
    }


    @ExceptionHandler(Exception.class)
    public ApiResult<Void> exceptionHandler(Exception e) {
        log.error("UnknownException: {}", e.toString());

        return ApiResult.error("Unknown Error");
    }

}