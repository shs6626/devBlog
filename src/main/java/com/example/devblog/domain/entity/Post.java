package com.example.devblog.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    @Setter @Column(nullable = false, length = 200) private String title;
    @Setter @Column(nullable = false, length = 10_000) private String content;

    @Column(nullable = false) private String userId;

    @Column(nullable = false, length = 14, columnDefinition = "DEFAULT DATE_FORMAT(NOW(), '%Y%m%d%H%i%s')") private String createdAt;
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

    protected Post() {}

    private Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.userId = "sayya";
    }

    public static Post of(String title, String content) {
        return new Post(title, content);
    }

}