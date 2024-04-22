package com.example.devblog.controller.response;


import com.example.devblog.domain.dto.PostDto;
import com.example.devblog.domain.dto.PostListDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostDeleteResponse {

    private PostListDto postListDto;

}