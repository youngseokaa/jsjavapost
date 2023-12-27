package com.example.post.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.logging.Logger;


import static com.example.post.common.ExceptionMessage.UNAUTHORIZED_EXPIRE;
@Slf4j
@Configuration
@RequiredArgsConstructor

public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secretKey;
    public static final String ACCESS_TOKEN = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";




    public void createToken(String loginId, HttpServletResponse response) {
        // Claim = Jwt Token에 들어갈 정보
        // Claim에 loginId를 넣어 줌으로써 나중에 loginId를 꺼낼 수 있음
        Claims claims = Jwts.claims();
        claims.put("loginId", loginId);
        long expireTimeMs = 10000;


        String accessToken = BEARER_PREFIX +
                Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        String utfAccessToken = URLEncoder.encode(accessToken, StandardCharsets.UTF_8);
        Cookie cookie = new Cookie(ACCESS_TOKEN, utfAccessToken);
        response.addCookie(cookie);
    }

    // Claims에서 loginId 꺼내기
    public static String getLoginId(String token, String secretKey) {
        return extractClaims(token, secretKey).get("loginId").toString();
    }

    // 밝급된 Token이 만료 시간이 지났는지 체크
    public static boolean isExpired(String token, String secretKey) {
        Date expiredDate = extractClaims(token, secretKey).getExpiration();
        // Token의 만료 날짜가 지금보다 이전인지 check
        return expiredDate.before(new Date());
    }

    // SecretKey를 사용해 Token Parsing
    private static Claims extractClaims(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}

