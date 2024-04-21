package com.example.devblog.controller.response;

import com.example.devblog.domain.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostSaveResponse {

    private Long postId;
    private String title;
    private String content;
    private String userId;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;

    public static PostSaveResponse from(PostDto dto) {
        return new PostSaveResponse(
                dto.postId(),
                dto.title(),
                dto.content(),
                dto.userId(),
                dto.createdAt(),
                dto.updatedAt(),
                dto.deletedAt()
        );
    }

}