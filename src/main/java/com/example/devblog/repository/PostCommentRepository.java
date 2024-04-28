package com.example.devblog.repository;

import com.example.devblog.domain.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    List<PostComment> findByPostIdAndDeletedAtIsNull(Long postId);

    Optional<PostComment> findByPostIdAndId(Long postId, Long postCommentId);

    @Modifying
    @Query(value = """
            UPDATE POST_COMMENT
            SET DELETED_AT = DATE_FORMAT(NOW(), '%Y%m%d%H%i%s')
            WHERE POST_COMMENT_ID = :postCommentId
           """,
           nativeQuery = true)
    void deleteComment(Long postCommentId);

}