package com.example.devblog.controller.request;

import com.example.devblog.domain.dto.PostDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostUpdateRequest {

    @NotBlank @Size(min=1, max=200, message = "Invalid Input")
    private String title;

    @NotBlank @Size(min=1, max=2000, message = "Invalid Input")
    private String content;

    public PostDto toDto() {
        return new PostDto(null, this.title, this.content, null, null, null, null);
    }

}