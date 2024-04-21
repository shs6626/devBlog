package com.example.devblog.service;

import com.example.devblog.domain.dto.PostCommentDto;
import com.example.devblog.domain.dto.PostDto;
import com.example.devblog.domain.entity.Post;
import com.example.devblog.domain.entity.PostComment;
import com.example.devblog.repository.PostCommentRepository;
import com.example.devblog.repository.PostRepository;
import com.example.devblog.utils.error.DevBlogException;
import com.example.devblog.utils.error.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PostCommentService {

    private final PostCommentRepository postCommentRepository;
    private final PostRepository postRepository;


    /** PostComment 목록 조회하기 */
    @Transactional
    public List<PostCommentDto> getPostComment(Long postId) {
        // TODO : 여기도 Paging 처리 해야되지 않나 ..?
        return postCommentRepository.findByPostIdAndDeletedAtIsNull(postId).stream().map(PostCommentDto::from).toList();
    }

    /** Post Comment 저장하기 */
    public List<PostCommentDto> savePostComment(Long postId, String content) {
        // 권한 인증
        // Post 존재여부 확인
        Post postEntity = findPostByPostId(postId);
        // Post Comment 저장
        postCommentRepository.save(PostComment.of(content, postId, "sayya"));

        return postCommentRepository.findByPostIdAndDeletedAtIsNull(postId).stream().map(PostCommentDto::from).toList();
    }

    /** Post Comment 수정하기 */
    @Transactional
    public List<PostCommentDto> updatePostComment(Long postId, Long postCommentId, String comment) {
        // 권한 인증
        // Post 존재여부 확인
        Post postEntity = findPostByPostId(postId);
        // Post Comment 존재 여부 확인
        PostComment postCommentEntity = postCommentRepository.findByPostIdAndPostCommentId(postId, postCommentId).orElseThrow(() ->
                new DevBlogException(ExceptionCode.POST_COMMENT_NOT_FOUND)
        );
        // Post Comment 수정
        postCommentEntity.setComment(comment);

        return postCommentRepository.findByPostIdAndDeletedAtIsNull(postId).stream().map(PostCommentDto::from).toList();
    }

    /**
     * Post Comment 삭제하기
     */
    @Transactional
    public List<PostCommentDto> deletePostComment(Long postId, Long postCommentId) {
        // 권한 인증
        // Post 존재여부 확인
        Post postEntity = findPostByPostId(postId);
        // Post Comment 존재 여부 확인
        PostComment postCommentEntity = postCommentRepository.findByPostIdAndPostCommentId(postId, postCommentId).orElseThrow(() ->
                new DevBlogException(ExceptionCode.POST_COMMENT_NOT_FOUND)
        );
        // postComment 삭제
        postCommentRepository.deleteComment(postCommentId);

        return postCommentRepository.findByPostIdAndDeletedAtIsNull(postId).stream().map(PostCommentDto::from).toList();
    }

    // Post 존재하는지 확인
    private Post findPostByPostId(Long postId) {
        return postRepository.findByIdAndDeletedAtIsNull(postId).orElseThrow(() ->
                new DevBlogException(ExceptionCode.POST_NOT_FOUND)
        );
    }


}