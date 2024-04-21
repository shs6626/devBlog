package com.example.devblog.domain;

import com.example.devblog.domain.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

    private Long id;
    private String userEmail;
    private UserRole userRole;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;

    public static User fromEntity(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getUserEmail(),
                userEntity.getUserRole(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt(),
                userEntity.getDeletedAt()
        );
    }

}