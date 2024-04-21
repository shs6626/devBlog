package com.example.devblog.domain.dto;

import com.example.devblog.domain.entity.Post;

public record PostDto(
    Long postId,
    String title,
    String content,
    String userId,
    String createdAt,
    String updatedAt,
    String deletedAt
){
    public static PostDto from(Post post) {
        return new PostDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUserId(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getDeletedAt()
        );
    }

}