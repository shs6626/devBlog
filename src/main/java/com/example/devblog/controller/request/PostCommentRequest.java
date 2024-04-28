package com.example.devblog.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostCommentRequest {

    @NotBlank(message = "Invalid Input")
    @Size(min=1, max=300, message = "Invalid Input")
    private String comment;

}