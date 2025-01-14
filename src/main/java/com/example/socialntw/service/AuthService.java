package com.example.socialntw.service;

import com.example.socialntw.dto.AuthorizationDto;
import com.example.socialntw.dto.LoginDto;
import com.example.socialntw.dto.TokenDto;
import com.example.socialntw.dto.UserDto;

public interface AuthService {
    UserDto registerNewUser(UserDto userDto);

    AuthorizationDto login(LoginDto loginDto);

    TokenDto refresh(String refreshTokenDto);

    void logout(String token);
}
