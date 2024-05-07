package com.example.CharityOrganization.utils.JWT_tokens;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import org.springframework.stereotype.Component;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Component
public class JwtTokenUtil {

    private final String secret_key = "5832f305944be40fc2f9ae4bc0f5b821f309270ea5fec436ec2026cd59e0cccf";

    public String generateToken(String username, String userRole, String tokenType) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 100))
                .claim("type", tokenType)
                .claim("role", userRole) // Add user role as a claim
                .signWith(getSigninKey())
                .compact();
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(secret_key);
        return new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    public String generateAccessToken(String username, String userRole) {
        return generateToken(username, userRole, "ACCESS");
    }

    public String generateRefreshToken(String username, String userRole) {
        return generateToken(username, userRole, "REFRESH");
    }


    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}