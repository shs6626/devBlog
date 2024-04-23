package com.example.devblog.controller;

import com.example.devblog.controller.request.PostSaveRequest;
import com.example.devblog.controller.request.PostUpdateRequest;
import com.example.devblog.controller.response.*;
import com.example.devblog.domain.dto.PostCommentDto;
import com.example.devblog.domain.dto.PostDto;
import com.example.devblog.domain.entity.Post;
import com.example.devblog.service.PostCommentService;
import com.example.devblog.service.PostService;
import com.example.devblog.utils.ApiResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    private final PostService postService;
    private final PostCommentService postCommentService;

    /** 게시글 목록 조회 */
    @GetMapping
    public ApiResult<PostListResponse> getPostList(@RequestParam(defaultValue = "1", required = false) String currentPage,
                                                   @RequestParam(defaultValue = "10", required = false) String pageSize,
                                                   @RequestParam(required = false) String searchKeyword) {
        return ApiResult.success(new PostListResponse(postService.getPostList(currentPage, pageSize, searchKeyword)));
    }

//    @GetMapping
//    public ApiResult<Page<Post>> getPostList2(@RequestParam(required = false, defaultValue = "1") String currentPage,
//                                              @RequestParam(required = false, defaultValue = "10") String pageSize,
//                                              @RequestParam(required = false, defaultValue = "createdAt") String sortBy,
//                                              @RequestParam(required = false, defaultValue = "desc") String direction,
//                                              @RequestParam(required = false) String searchKeyword) {
//        Pageable pageable = getPageable(currentPage, pageSize, sortBy, direction);
//        return ApiResult.success(postService.getPostList2(pageable, searchKeyword));
//    }

    @GetMapping("/pageable")
    public ApiResult<Page<Post>> getPostList2(@Valid @PageableDefault(page = 1, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                              @RequestParam(required = false) String searchKeyword) {
        return ApiResult.success(postService.getPostList2(pageable, searchKeyword));
    }

    /** 게시글 상세 조회 */
    @GetMapping("/{post_id}")
    public ApiResult<PostGetResponse> getPost3(@PathVariable(value = "post_id") Long postId) {
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
        return ApiResult.success(new PostDeleteResponse(postService.getPostList("1", "10", "")));
    }

    private Pageable getPageable(String currentPage_, String pageSize_, String sortBy, String direction) {
        int currentPage = (currentPage_.chars().allMatch(Character::isDigit)) ? Integer.parseInt(currentPage_) : 1;
        int pageSize = (pageSize_.chars().allMatch(Character::isDigit)) ? Integer.parseInt(pageSize_) : 10;

        if ("asc".equals(direction)) {
            return PageRequest.of(currentPage, pageSize, Sort.by(sortBy));
        }
        return PageRequest.of(currentPage, pageSize, Sort.by(sortBy).descending());
    }

}