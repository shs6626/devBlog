package com.example.devblog.controller.response;

import com.example.devblog.domain.User;
import com.example.devblog.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinResponse {

    private Long id;
    private String userEmail;
    private UserRole role;

    public static UserJoinResponse fromUser(User user) {
        return new UserJoinResponse(
                user.getId(),
                user.getUserEmail(),
                user.getUserRole()
        );
    }

}