package com.example.devblog.config;

import com.example.devblog.utils.jwt.LoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    private final AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //AuthenticationManager Bean 등록
//        @Bean
//        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//            return configuration.getAuthenticationManager();
//        }

        // csrf disable - jwt는 csrf로부터 안전하다
        http.csrf(AbstractHttpConfigurer::disable);
        // jwt는 form 로그인 방식도 필요 없음
        http.formLogin(AbstractHttpConfigurer::disable);
        // http basic 인증 방식도 disable
        http.httpBasic(AbstractHttpConfigurer::disable);
        //
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/login", "/join", "/api/**", "/api/**/comment").permitAll()
//                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
        );
        // filter 설정
//        http.addFilterAt(new LoginFilter(), UsernamePasswordAuthenticationFilter.class);
        // 세션 설정
        http.sessionManagement((session) -> session
                .sessionCreationPolicy((SessionCreationPolicy.STATELESS))
        );

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
