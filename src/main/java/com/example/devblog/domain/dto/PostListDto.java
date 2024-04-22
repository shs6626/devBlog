package com.example.devblog.domain.dto;

import com.example.devblog.utils.paging.PagingInfo;

import java.util.List;

public record PostListDto(
        List<PostDto> postDtoList,
        PagingInfo pagingInfo
) {

    public static PostListDto of(List<PostDto> postDtoList, PagingInfo pagingInfo) {
        return new PostListDto(postDtoList, pagingInfo);
    }

}