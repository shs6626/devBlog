package com.example.devblog.service;

import com.example.devblog.domain.User;
import com.example.devblog.domain.entity.UserEntity;
import com.example.devblog.repository.UserRepository;
import com.example.devblog.utils.error.DevBlogException;
import com.example.devblog.utils.error.ExceptionCode;
import com.example.devblog.utils.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Value("${jwt.secret-key}") private String secretKey;
    @Value("${jwt.token.expired-time-ms}") private Long expiredTimeMs;


    public User join(String userEmail, String password) {
        // 1. 회원가입이 되어있는지 확인
        userRepository.findByUserEmail(userEmail).ifPresent(it -> {
                throw new DevBlogException(ExceptionCode.MEMBER_EXISTS);
        });
        // 2. 회원가입 진행
        UserEntity userEntity = userRepository.save(UserEntity.of(userEmail, encoder.encode(password)));

        return User.fromEntity(userEntity);
    }

    public String login(String userEmail, String password) {
        // 1. 회원가입여부 확인
        UserEntity userEntity = userRepository.findByUserEmail(userEmail).orElseThrow(
                () ->  new DevBlogException(ExceptionCode.MEMBER_EXISTS)
        );
        // 2. 비밀번호 체크
        if (encoder.matches(password, userEntity.getPassword())) {
            throw new DevBlogException(ExceptionCode.INVALID_PASSWORD);
        }
        // 3. Token 발급
        return JwtTokenUtil.generateToken(userEmail, secretKey, expiredTimeMs);
    }

}
