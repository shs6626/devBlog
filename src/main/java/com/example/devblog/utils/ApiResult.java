package com.example.devblog.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter @Setter @Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult<T> {

    private String returnMessage;
    private T responseData;

    public static <T> ApiResult<T> success() {
        return new ApiResult<>("SUCCESS", null);
    }

    public static <T> ApiResult<T> success(T result) {
        return new ApiResult<>("SUCCESS", result);
    }

    public static ApiResult<Void> error(String message) {
        return new ApiResult<>(message, null);
    }

}