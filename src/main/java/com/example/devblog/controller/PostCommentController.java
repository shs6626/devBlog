package com.example.devblog.controller;

import com.example.devblog.service.PostCommentService;
import com.example.devblog.utils.ApiResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/comment")
public class PostCommentController {

    private final PostCommentService postCommentService;

    /** 댓글 저장 */
    @PostMapping("/{post_id}")
    public ApiResult<Void> savePostComment(@PathVariable(value = "post_id") Long postId,
                                           @Valid @RequestBody String content) {
        postCommentService.savePostComment(postId, content);
        return ApiResult.success();
    }


    /** 댓글 수정 */
    @PutMapping("/{post_id}/{post_comment_id}")
    public ApiResult<Void> updatePostComment(@PathVariable(value = "post_id") Long postId,
                                             @PathVariable(value = "post_comment_id") Long postCommentId,
                                             @Size(min = 1, max = 300) @RequestBody String comment) {
        postCommentService.updatePostComment(postId, postCommentId, comment);
        return ApiResult.success();
    }

    /** 댓글 삭제 */
    @DeleteMapping("/{post_id}/{post_comment_id}")
    public ApiResult<Void> deletePostComment(@PathVariable(value = "post_id") Long postId,
                                             @PathVariable(value = "post_comment_id") Long postCommentId) {
        postCommentService.deletePostComment(postId, postCommentId);
        return ApiResult.success();
    }

}