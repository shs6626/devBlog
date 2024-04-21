package com.example.devblog.controller;

import com.example.devblog.controller.response.PostGetResponse;
import com.example.devblog.domain.dto.PostCommentDto;
import com.example.devblog.domain.dto.PostDto;
import com.example.devblog.service.PostCommentService;
import com.example.devblog.service.PostService;
import com.example.devblog.utils.ApiResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/comment")
public class PostCommentController {

    private final PostCommentService postCommentService;
    private final PostService postService;

    /** 댓글 저장 */
    @PostMapping("/{post_id}")
    public ApiResult<PostGetResponse> savePostComment(@PathVariable(value = "post_id") Long postId,
                                                      @Valid @RequestBody String content) {
        List<PostCommentDto> postCommentDtoList = postCommentService.savePostComment(postId, content);
        PostDto postDto = postService.getPost(postId);

        return ApiResult.success(PostGetResponse.from(postDto, postCommentDtoList));
    }


    /** 댓글 수정 */
    @PutMapping("/{post_id}/{post_comment_id}")
    public ApiResult<PostGetResponse> updatePostComment(@PathVariable(value = "post_id") Long postId,
                                                        @PathVariable(value = "post_comment_id") Long postCommentId,
                                                        @RequestBody String comment) {
        List<PostCommentDto> postCommentDtoList = postCommentService.updatePostComment(postId, postCommentId, comment);
        PostDto postDto = postService.getPost(postId);

        return ApiResult.success(PostGetResponse.from(postDto, postCommentDtoList));
    }

    /** 댓글 삭제 */
    @DeleteMapping("/{post_id}/{post_comment_id}")
    public ApiResult<PostGetResponse> deletePostComment(@PathVariable(value = "post_id") Long postId,
                                                        @PathVariable(value = "post_comment_id") Long postCommentId) {
        List<PostCommentDto> postCommentDtoList = postCommentService.deletePostComment(postId, postCommentId);
        PostDto postDto = postService.getPost(postId);

        return ApiResult.success(PostGetResponse.from(postDto, postCommentDtoList));
    }

}