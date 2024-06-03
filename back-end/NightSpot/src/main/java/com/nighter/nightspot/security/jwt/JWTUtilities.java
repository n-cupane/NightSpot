package com.nighter.nightspot.security.jwt;

import com.nighter.nightspot.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JWTUtilities {

    private static final String PRIVATE_KEY = "Qv6N$KeLbu52RoxEvTZesUWwLBnm*7uB24Dg*CygAxwmhKLC!kiQY34Jq48^x$^t#2sU$Qxm*aThd%@LpUMRfpzWpo2MEea*vY5WeuToLHpDiNuiTVciSro8yJrV2vq2";

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(PRIVATE_KEY.getBytes());
    }

//    Generates token
    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 30)))
                .claims()
                .add("userRole", user.getRole().getName())
                .and()
                .signWith(getSignInKey())
                .compact();
    }

//    Reads payload
    public Claims getPayload(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean itTokenValid(String token) {

        Claims claims = getPayload(token);
        String userRole = claims.get("userRole", String.class);
        return claims.getExpiration().before(new Date(System.currentTimeMillis())) && userRole != null;

    }

    public <T> T extractClaim(String token, Function<Claims, T> function) {
        Claims claims = getPayload(token);
        return function.apply(claims);
    }

}
