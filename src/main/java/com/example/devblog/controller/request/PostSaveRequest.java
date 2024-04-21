package com.example.devblog.controller.request;

import com.example.devblog.domain.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostSaveRequest {

    private String title;
    private String content;
    private String userId;
    private String createdAt;

    public PostDto toDto() {
        return new PostDto(null, this.title, this.content, null, null, null, null);
    }

}