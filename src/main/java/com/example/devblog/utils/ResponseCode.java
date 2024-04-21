package com.example.devblog.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    SUCCESS("0000", "SUCCESS"),
    NOT_IDENTITY_VERIFIED_USER("4000", "The user's identity has not been verified."),
    UNKNOWN_ERROR("9999", "Unable to process your request. Please try again later.");

    private final String returnCode;
    private final String returnMessage;

}