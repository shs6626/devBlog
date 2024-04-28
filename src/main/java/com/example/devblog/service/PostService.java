package com.example.devblog.service;

import com.example.devblog.domain.dto.PostDto;
import com.example.devblog.domain.dto.PostListDto;
import com.example.devblog.domain.entity.Post;
import com.example.devblog.domain.entity.PostComment;
import com.example.devblog.repository.PostCommentRepository;
import com.example.devblog.repository.PostRepository;
import com.example.devblog.utils.error.DevBlogException;
import com.example.devblog.utils.error.ExceptionCode;
import com.example.devblog.utils.paging.PagingInfo;
import com.example.devblog.utils.paging.PagingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostCommentService postCommentServic;
    private final PostRepository postRepository;
    private final PostCommentRepository postCommentRepository;

    /** 게시글 목록 조회 */
    @Transactional(readOnly = true)
    public PostListDto getPostList(String currentPage, String pageSize, String searchKeyword, String sortBy) {
        // Paging 처리를 위한 - 전체 RowData 가져오기
        Long totalRowDataCnt;
        if (searchKeyword == null || searchKeyword.isBlank())
            totalRowDataCnt = postRepository.countByDeletedAtIsNull();
        else
            totalRowDataCnt = postRepository.countDeletedAtIsNullContains(searchKeyword);

        // Paging 처리
        PagingInfo pagingInfo = PagingUtils.getPagingInfo(currentPage, pageSize, totalRowDataCnt);
        List<Post> postList = postRepository.selectPostList(pagingInfo.getStartRowDataNum()-1,pagingInfo.getPageSize(),searchKeyword,sortBy);

        return PostListDto.of(postList.stream().map(PostDto::from).toList(), pagingInfo);
    }

    /** 게시글 상세 조회 */
    @Transactional(readOnly = true)
    public PostDto getPost(long postId) {
        // 접근 권한 확인
        // 1. Post 가져오기
        Post postEntity = postRepository.findByIdAndDeletedAtIsNull(postId).orElseThrow(() ->
                new DevBlogException(ExceptionCode.POST_NOT_FOUND)
        );
        // 2. PostComment 가져오기
        List<PostComment> li = postCommentRepository.findByPostIdAndDeletedAtIsNullOrderByCreatedAtAsc(postId);

        return PostDto.from(postEntity);
    }

    /** 게시글 저장 */
    @Transactional
    public PostDto savePost(PostDto dto) {
        Post postEntity = postRepository.save(Post.of(dto.title(), dto.content()));
        return PostDto.from(postEntity);
    }


    /** 게시글 수정 */
    @Transactional
    public void updatePost(long postId, PostDto dto) {
        // 2. Post 존재여부 확인
        Post postEntity = postRepository.findByIdAndDeletedAtIsNull(postId).orElseThrow(() ->
                new DevBlogException(ExceptionCode.POST_NOT_FOUND)
        );
        // 3. 변경 부분 수정
        if (postEntity.getTitle().equals(dto.title()) && postEntity.getContent().equals(dto.content())) {
            throw new DevBlogException(ExceptionCode.POST_DOES_NOT_BE_CHANGED_ANYTHING);
        }
        postEntity.setTitle(dto.title());
        postEntity.setContent(dto.content());
    }


    /** 게시글 삭제 */
    @Transactional
    public void deletePost(long postId) {
        // 1. 권한 인증
        // 2. Post 존재여부 확인
        Post postEntity = postRepository.findByIdAndDeletedAtIsNull(postId).orElseThrow(() ->
                new DevBlogException(ExceptionCode.POST_NOT_FOUND)
        );
        postRepository.deletePost(postId);
    }

}