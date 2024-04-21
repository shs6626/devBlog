package com.example.devblog.controller;

import com.example.devblog.controller.request.UserJoinRequest;
import com.example.devblog.controller.request.UserLoginRequest;
import com.example.devblog.controller.response.UserJoinResponse;
import com.example.devblog.controller.response.UserLoginResponse;
import com.example.devblog.domain.User;
import com.example.devblog.service.UserService;
import com.example.devblog.utils.ApiResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ApiResult<UserJoinResponse> join(@Valid @RequestBody UserJoinRequest request) {
        User user = userService.join(request.getUserEmail(), request.getPassword());
        return ApiResult.success(UserJoinResponse.fromUser(user));
    }


    @PostMapping("/login")
    public ApiResult<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest request) {
        String token = userService.login(request.getUserEamil(), request.getPassword());
        return ApiResult.success(new UserLoginResponse(token));
    }


}