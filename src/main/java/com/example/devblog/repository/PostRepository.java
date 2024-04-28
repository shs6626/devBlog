package com.example.devblog.repository;

import com.example.devblog.domain.dto.PostDto;
import com.example.devblog.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 전체 게시글 수 구하기
    Long countByDeletedAtIsNull();

    // 검색하는 경우 - 전체 게시글 수 구하기
    @Query(value = """
             SELECT COUNT(*)
             FROM POST
             WHERE DELETED_AT IS NULL
               AND TITLE LIKE CONCAT('%', :searchKeyword, '%')
            """,
            nativeQuery = true)
    Long countDeletedAtIsNullContains(String searchKeyword);

    @Query(value = """
             SELECT *
             FROM POST
             WHERE DELETED_AT IS NULL
               AND TITLE LIKE CONCAT('%', :searchKeyword, '%')
             ORDER BY CASE WHEN :sortBy = 'CREATED_AT' THEN CREATED_AT
                           WHEN :sortBy = 'TITLE' THEN TITLE
                           WHEN :sortBy = 'USER_ID' THEN TITLE
                           ELSE POST_ID
                           END DESC
             LIMIT :startRowDataNum, :pageSize
            """,
            nativeQuery = true)
    List<Post> selectPostList(@Param("startRowDataNum") int startRowDataNum,
                              @Param("pageSize") int pageSize,
                              @Param("searchKeyword") String searchKeyword,
                              @Param("sortBy") String sortBy);

    Optional<Post> findByIdAndDeletedAtIsNull(Long postId);

    // 게시글 삭제
    @Modifying
    @Query(value = """
             UPDATE POST
             SET DELETED_AT = DATE_FORMAT(NOW(), '%Y%m%d%H%i%s')
             WHERE POST_ID = :postId
            """,
           nativeQuery = true)
    void deletePost(long postId);



}