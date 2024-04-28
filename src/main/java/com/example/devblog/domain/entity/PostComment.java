package com.example.devblog.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
public class PostComment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_COMMENT_ID")
    private Long id;

    @Setter @Column(nullable = false, length = 300) private String comment;

    @Column(nullable = false) private Long postId;
    @Column(nullable = false) private String userId;

    @Column(nullable = false, length = 14) private String createdAt;
    @Column(length = 14) private String updatedAt;
    @Column(length = 14) private String deletedAt;

    @PrePersist
    public void setCreatedAt() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        this.createdAt = currentDateTime.format(formatter);
    }

    @PreUpdate
    public void setUpdatedAt() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        this.updatedAt = currentDateTime.format(formatter);
    }


    protected PostComment() {}

    private PostComment(String comment, Long postId, String userId) {
        this.comment = comment;
        this.postId = postId;
        this.userId = userId;
    }

    public static PostComment of(String comment, Long postId, String userId) {
        return new PostComment(comment, postId, userId);
    }

}