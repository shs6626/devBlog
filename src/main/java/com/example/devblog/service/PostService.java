package com.example.devblog.service;

import com.example.devblog.domain.dto.PostDto;
import com.example.devblog.domain.dto.PostListDto;
import com.example.devblog.domain.entity.Post;
import com.example.devblog.repository.PostCommentRepository;
import com.example.devblog.repository.PostRepository;
import com.example.devblog.utils.error.DevBlogException;
import com.example.devblog.utils.error.ExceptionCode;
import com.example.devblog.utils.paging.PagingInfo;
import com.example.devblog.utils.paging.PagingUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostCommentService postCommentServic;
    private final PostRepository postRepository;
    private final PostCommentRepository postCommentRepository;

    /** 게시글 목록 조회 */
    @Transactional(readOnly = true)
    public PostListDto getPostList(String currentPage, String pageSize, String searchKeyword) {
        long totalRowDataCnt = postRepository.countByDeletedAtIsNull();
        PagingInfo pagingInfo = PagingUtils.getPagingInfo(currentPage, pageSize, totalRowDataCnt);

        // 접근 권한이 있는지 확인해야함
        int startRowDataNum = pagingInfo.getStartRowDataNum()-1;
        int pageSize_ = pagingInfo.getPageSize();

        List<Post> postList;
        if (searchKeyword == null || searchKeyword.isBlank()) {
            postList = postRepository.findAllWithPaging(startRowDataNum, pageSize_);
        } else {
            postList = postRepository.findBySearchKewordWithPaging(startRowDataNum,pageSize_,searchKeyword);
        }
        return PostListDto.of(postList.stream().map(PostDto::from).toList(), pagingInfo);
    }

    @Transactional(readOnly = true)
    public Page<Post> getPostList2(Pageable pageable, String searchKeyword) {
        return postRepository.findByDeletedAtIsNull(pageable);
    }

    /** 게시글 상세 조회 */
    @Transactional(readOnly = true)
    public PostDto getPost(long postId) {
        // 접근 권한 확인
        // 1. Post 가져오기
        Post postEntity = postRepository.findByIdAndDeletedAtIsNull(postId).orElseThrow(() ->
                new DevBlogException(ExceptionCode.POST_NOT_FOUND)
        );

        return PostDto.from(postEntity);
    }

    /** 게시글 저장 */
    @Transactional
    public PostDto savePost(PostDto dto) {
        // 접근 권한 인증
        // Post 저장
        Post postEntity = postRepository.save(Post.of(dto.title(), dto.content()));

        return PostDto.from(postEntity);
    }


    /** 게시글 수정 */
    @Transactional
    public PostDto updatePost(long postId, PostDto dto) {
        // 1. 권한 인증

        // 2. Post 존재여부 확인
        Post postEntity = postRepository.findByIdAndDeletedAtIsNull(postId).orElseThrow(() ->
                new DevBlogException(ExceptionCode.POST_NOT_FOUND)
        );
        // 3. 변경 부분 수정
        if (!postEntity.getTitle().equals(dto.title()))
            postEntity.setTitle(dto.title());

        if (!postEntity.getContent().equals(dto.content()))
            postEntity.setContent(dto.content());

        return PostDto.from(postEntity);
    }


    /** 게시글 삭제 */
    @Transactional
    public void deletePost(long postId) {
        // 1. 권한 인증

        // 2. Post 존재여부 확인
        Post postEntity = postRepository.findByIdAndDeletedAtIsNull(postId).orElseThrow(() ->
                new DevBlogException(ExceptionCode.POST_NOT_FOUND)
        );
        postRepository.deletePostById(postId);
    }

}