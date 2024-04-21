package com.example.devblog.domain.dto;

import com.example.devblog.domain.entity.PostComment;

public record PostCommentDto(
    Long postCommentId,
    String comment,
    Long postId,
    String userId,
    String createdAt,
    String updatedAt,
    String deletedAt
){

    public static PostCommentDto from(PostComment entity) {
        return new PostCommentDto(
            entity.getId(),
            entity.getComment(),
            entity.getPostId(),
            entity.getUserId(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            entity.getDeletedAt()
        );
    }

}