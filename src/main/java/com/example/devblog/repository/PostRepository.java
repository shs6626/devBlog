package com.example.devblog.repository;

import com.example.devblog.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByIdAndDeletedAtIsNull(Long postId);

    Page<Post> findAllByDeletedAtNull(Pageable pageable);

    @Modifying
    @Query(value = """
             UPDATE POST
             SET DELETED_AT = DATE_FORMAT(NOW(), '%Y%m%d%H%i%s')
             WHERE POST_ID = :postId
            """,
           nativeQuery = true)
    void deletePostById(long postId);

}