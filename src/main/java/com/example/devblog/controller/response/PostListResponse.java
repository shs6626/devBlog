package com.example.devblog.controller.response;

import com.example.devblog.domain.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public class PostListResponse {

    private Page<PostDto> postDtoPage;

}