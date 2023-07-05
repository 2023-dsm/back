package com.example.back.global.jwt;


import com.example.back.global.auth.AuthDetails;
import com.example.back.global.auth.AuthDetailsService;
import com.example.back.global.auth.CompanyDetails;
import com.example.back.global.auth.CompanyDetailsService;
import com.example.back.global.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
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
    private final CompanyDetailsService companyDetailsService;

    @Value("${auth.jwt.secret}")
    private String secretKey;

    private static final String HEADER = "Authorization";

    private static final String PREFIX = "Bearer";

    public String getUserAccessToken(Long userId) {
        return generateAccessToken(userId, "user");
    }

    public String getCompanyAccessToken(Long userId) {
        return generateAccessToken(userId, "company");
    }

    private String generateAccessToken(Long id, String typ) {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .setIssuedAt(new Date())
                .setSubject(id.toString())
                .setHeaderParam("typ", typ)
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
        JwsHeader typ = getHeader(token);

        if(typ.getType().equals("user")) {
            AuthDetails authDetails = authDetailsService.loadUserByUsername(getId(token));
            return new UsernamePasswordAuthenticationToken(authDetails, "", authDetails.getAuthorities());
        } else {
            CompanyDetails companyDetails = companyDetailsService.loadUserByUsername(getId(token));
            return new UsernamePasswordAuthenticationToken(companyDetails, "", companyDetails.getAuthorities());
        }



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

    private JwsHeader getHeader(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getHeader();
    }

}