package com.example.devblog.controller.request;

import com.example.devblog.domain.dto.PostDto;

public class PostUpdateRequest {

    private String title;
    private String content;

    public PostDto toDto() {
        return new PostDto(
                null,
                this.title,
                this.content,
                null,
                null,
                null,
                null
        );
    }

}