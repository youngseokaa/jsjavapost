package com.example.post.common;

import com.example.post.Controller.VIewReturnController;
import com.example.post.Service.MemberService;
import com.example.post.entity.Member;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {


        private final JwtTokenUtil jwtTokenUtil;
        @Value("${jwt.secret}")
        private String secretKey;

    @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = null;
        String checkCookie = request.getHeader("cookie");
        int start = -1;

        if(checkCookie != null){
        start = checkCookie.indexOf("Authorization=");
        }

        if (start != -1) {
            int end = checkCookie.indexOf(";", start);
            authorizationHeader = end != -1 ? checkCookie.substring(start + "Authorization=".length(), end) : checkCookie.substring(start + "Authorization=".length());
            authorizationHeader = URLDecoder.decode(authorizationHeader, StandardCharsets.UTF_8);
        }

        if(authorizationHeader == null) {
                filterChain.doFilter(request, response);
                return;
            }

            // Header의 Authorization 값이 'Bearer '로 시작하지 않으면 => 잘못된 토큰
            if(!authorizationHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            // 전송받은 값에서 'Bearer ' 뒷부분(Jwt Token) 추출
            String token = authorizationHeader.split(" ")[1];

            // 전송받은 Jwt Token이 만료되었으면 => 다음 필터 진행(인증 X)
            try{
                if(JwtTokenUtil.isExpired(token, secretKey)) {
                    filterChain.doFilter(request, response);
                    return;
                }
            }catch(ExpiredJwtException expiredJwtException){
                Cookie cookie = new Cookie("Authorization", null);
                response.addCookie(cookie);
                filterChain.doFilter(request, response);
                return;
            }

            // Jwt Token에서 loginId 추출
            String loginId = JwtTokenUtil.getLoginId(token, secretKey);

            setAuthentication(loginId);
            filterChain.doFilter(request, response);
        }

    public void setAuthentication(String loginid) {
        Authentication authentication = jwtTokenUtil.createAuthentication(loginid);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
