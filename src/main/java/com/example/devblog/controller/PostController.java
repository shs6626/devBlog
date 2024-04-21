package com.example.devblog.controller;

import com.example.devblog.controller.request.PostSaveRequest;
import com.example.devblog.controller.request.PostUpdateRequest;
import com.example.devblog.controller.response.*;
import com.example.devblog.domain.dto.*;
import com.example.devblog.service.PostCommentService;
import com.example.devblog.service.PostService;
import com.example.devblog.utils.ApiResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostCommentService postCommentService;

    /** 게시글 목록 조회 */
    @GetMapping
    public ApiResult<PostListResponse> getPostList(@RequestParam(defaultValue = "1", required = false) String currentPage,
                                                   @RequestParam(defaultValue = "10", required = false) String pageSize,
                                                   @RequestParam(defaultValue = "createdAt", required = false) String sort,
                                                   @RequestParam(defaultValue = "", required = false) String searchKeyword) {
        System.out.println(currentPage);
        System.out.println(pageSize);
        Pageable pageable = PageRequest.of(Integer.parseInt(currentPage), Integer.parseInt(pageSize), Sort.by(sort).descending());
        return ApiResult.success(new PostListResponse(postService.getPostList(pageable)));
    }

    /** 게시글 상세 조회 */
    @GetMapping("/{post_id}")
    public ApiResult<PostGetResponse> getPost(@PathVariable(value = "post_id") Long postId) {
        PostDto postDto = postService.getPost(postId);
        List<PostCommentDto> postCommentDtoList = postCommentService.getPostComment(postId);

        return ApiResult.success(PostGetResponse.from(postDto, postCommentDtoList));
    }

    /** 게시글 저장 */
    @PostMapping
    public ApiResult<PostSaveResponse> savePost(@Valid @RequestBody PostSaveRequest request) {
        PostDto postDto = postService.savePost(request.toDto());
        return ApiResult.success(PostSaveResponse.from(postDto));
    }

    /** 게시글 수정 */
    @PutMapping("/{post_id}")
    public ApiResult<PostUpdateResponse> updatePost(@PathVariable(value = "post_id") Long postId,
                                                    @Valid @RequestBody PostUpdateRequest request) {
        PostDto postDto = postService.updatePost(postId, request.toDto());
        List<PostCommentDto> postCommentDtoList = postCommentService.getPostComment(postId);

        return ApiResult.success(PostUpdateResponse.from(postDto, postCommentDtoList));
    }

    /** 게시글 삭제 */
    @DeleteMapping("/{post_id}")
    public ApiResult<PostDeleteResponse> deletePost(@PathVariable(value = "post_id") Long postId) {
        postService.deletePost(postId);

        Pageable pageable = PageRequest.of(1,10,Sort.by("createdAt").descending());
        return ApiResult.success(new PostDeleteResponse(postService.getPostList(pageable)));
    }

}