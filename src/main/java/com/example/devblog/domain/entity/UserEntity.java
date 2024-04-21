package com.example.devblog.domain.entity;

import com.example.devblog.domain.UserRole;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;

@Getter
@Table(name = "USER_ACCOUNT")
@Entity
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID") private Long id;

    @Column(nullable = false, length = 50) private String userEmail;
    @Column(nullable = false, length = 50) private String password;

    @Column @Enumerated(EnumType.STRING) private UserRole userRole = UserRole.USER;

    @Column(nullable = false, length = 14) private String createdAt;
    @Column(length = 14) private String updatedAt;
    @Column(length = 14) private String deletedAt;

    @PrePersist
    public void createdAt() {
        Timestamp timestamp = Timestamp.from(Instant.now());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        this.createdAt = dateFormat.format(timestamp);
    }

    @PreUpdate
    public void updatedAt() {
        Timestamp timestamp = Timestamp.from(Instant.now());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        this.updatedAt = dateFormat.format(timestamp);
    }


    protected UserEntity() {}

    private UserEntity(String userEmail, String password) {
        this.password = password;
        this.userEmail = userEmail;
    }

    public static UserEntity of(String userEmail, String password) {
        return new UserEntity(userEmail, password);
    }


}