package com.example.devblog.repository;

import com.example.devblog.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByIdAndDeletedAtIsNull(Long postId);

    @Modifying
    @Query(value = """
             UPDATE POST
             SET DELETED_AT = DATE_FORMAT(NOW(), '%Y%m%d%H%i%s')
             WHERE POST_ID = :postId
            """,
           nativeQuery = true)
    void deletePostById(long postId);

    @Query(value = """
              SELECT *
              FROM POST
              WHERE DELETED_AT IS NULL
              ORDER BY CREATED_AT DESC
              LIMIT :startRowDataNum, :pageSize
            """,
           nativeQuery = true)
    List<Post> findAllWithPaging(@Param("startRowDataNum") int startRowDataNum,
                                 @Param("pageSize") int pageSize);


    @Query(value = """
             SELECT COUNT(*)
             FROM POST
             WHERE DELETED_AT IS NULL
               AND TITLE LIKE CONCAT('%', :searchKeyword, '%')
            """,
           nativeQuery = true)
    int countDeletedAtIsNullContains(String searchKeyword);

    int countByDeletedAtIsNull();

}