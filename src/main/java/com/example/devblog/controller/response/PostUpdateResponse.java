package com.example.devblog.controller.response;

import com.example.devblog.domain.dto.PostCommentDto;
import com.example.devblog.domain.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostUpdateResponse {

    private Long postId;
    private String title;
    private String content;
    private String userId;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;
    private List<PostCommentDto> postCommentDtoList;

    public static PostUpdateResponse from(PostDto postDto, List<PostCommentDto> postCommentDtoList) {
        return new PostUpdateResponse(
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