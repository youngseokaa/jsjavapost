package com.example.post.common;

import com.example.post.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberService memberService;

    @Value("${jwt.secret}")
    private  String secretKey ;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
             http
                .csrf().disable()
                .headers(headers -> headers.frameOptions().sameOrigin())	// H2 콘솔 사용을 위한 설정
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/SignUp").authenticated()	// requestMatchers의 인자로 전달된 url은 모두에게 허용
                                .anyRequest().permitAll()	// 그 외의 모든 요청은 인증 필요
                                .and()
                                .addFilterBefore(new JwtTokenFilter(memberService,secretKey), UsernamePasswordAuthenticationFilter.class)

                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
                 return http.build();// 세션을 사용하지 않으므로 STATELESS 설정



    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}