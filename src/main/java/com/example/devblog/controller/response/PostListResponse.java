package com.example.devblog.controller.response;

import com.example.devblog.domain.dto.PostListDto;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class PostListResponse {

    private PostListDto postListDto;

}