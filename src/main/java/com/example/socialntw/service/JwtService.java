package com.example.socialntw.service;

import com.example.socialntw.entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.function.Function;

public interface JwtService {
    String extractUsername(String jwt);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String generateAccessToken(User user);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(User user);

    boolean isTokenOfType(String token, String tokenType);

    Collection<? extends GrantedAuthority> getCurrentUserType();

}
