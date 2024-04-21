package com.example.devblog.controller.response;

import com.example.devblog.domain.dto.PostCommentDto;
import com.example.devblog.domain.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostGetResponse {

    Long postId;
    String title;
    String content;
    String userId;
    String createdAt;
    String updatedAt;
    String deletedAt;

    List<PostCommentDto> postCommentDtoList;

    public static PostGetResponse from(PostDto postDto, List<PostCommentDto> postCommentDtoList) {
        return new PostGetResponse(
                postDto.postId(),
                postDto.title(),
                postDto.content(),
                postDto.userId(),
                postDto.createdAt(),
                postDto.updatedAt(),
                postDto.deletedAt(),
                postCommentDtoList
        );
    }

}