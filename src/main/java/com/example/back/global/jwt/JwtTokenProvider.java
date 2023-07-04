package com.example.back.global.jwt;


import com.example.back.global.auth.AuthDetails;
import com.example.back.global.auth.AuthDetailsService;
import com.example.back.global.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private final AuthDetailsService authDetailsService;

    @Value("${auth.jwt.secret}")
    private String secretKey;

    private static final String HEADER = "Authorization";

    private static final String PREFIX = "Bearer";

    public String getAccessToken(Long userId) {
        return generateAccessToken(userId);
    }

    private String generateAccessToken(Long id) {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .setIssuedAt(new Date())
                .setSubject(id.toString())
                .setHeaderParam("typ", "access")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HEADER);

        if(bearerToken != null && bearerToken.startsWith(PREFIX)){
            return bearerToken.substring(7);
        }

        return null;
    }
    public Authentication authentication(String token) {
        AuthDetails authDetails = authDetailsService.loadUserByUsername(getId(token));
        return new UsernamePasswordAuthenticationToken(authDetails, "", authDetails.getAuthorities());
    }

    private String getId(String token) {
        try {
            return getBody(token).getSubject();
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    private Claims getBody(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody();
    }
}