package com.revatureproject.util;



import java.security.Key;
import java.util.Date;

import com.revatureproject.models.Users;
import com.revatureproject.exception.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenManager {

    private final Key key;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public JwtTokenManager() {

        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    }

    public String issueToken(Users user) {

        return Jwts.builder()
                // payload
                .setId(String.valueOf(user.getId()))
                .setSubject(user.getUsername())
                .setIssuer("Rolodex API")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(key).compact();

    }


    public int parseUserIdFromToken(String token) {

        try {
            return Integer.parseInt(Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token).getBody().getId());

        } catch (Exception e){
            logger.warn("JWT error parsing user id from token");
            throw new AuthenticationException("Unable to parse user id from JWT. Please sign in again");
        }

    }
}
