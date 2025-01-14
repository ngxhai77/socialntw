package com.example.socialntw.service.implement;

import com.example.socialntw.dto.AuthorizationDto;
import com.example.socialntw.dto.LoginDto;
import com.example.socialntw.dto.TokenDto;
import com.example.socialntw.dto.UserDto;
import com.example.socialntw.entity.User;
import com.example.socialntw.enums.UserType;
import com.example.socialntw.exception.BadRequestException;
import com.example.socialntw.exception.NotFoundException;
import com.example.socialntw.exception.UnauthorizedException;
import com.example.socialntw.service.AuthService;
import com.example.socialntw.service.JwtService;
import com.example.socialntw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.socialntw.common.constant.MessageConstant.*;


@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;


    @Override
    public UserDto registerNewUser(UserDto userDto) {
        if (userDto.getUserType() == null){
            throw new BadRequestException(FIELD_INVALID);
        }else if (userDto.getUserType() != UserType.STUDENT && userDto.getUserType() != UserType.INSTRUCTOR) {
            throw new BadRequestException(USER_TYPE_INVALID);
        }
        return userService.addUser(userDto);
    }


    @Override
    public AuthorizationDto login(LoginDto loginDto) {
        User user = userService.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new BadRequestException(EMAIL_PASSWORD_INVALID));
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPasswordHash())) {
            throw new BadRequestException(EMAIL_PASSWORD_INVALID);
        }
        if (!user.getIsActive()) {
            throw new BadRequestException(USER_BLOCKED);
        }
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        user.setRefreshToken(refreshToken);
        userService.saveUser(user);

        UserDto userDto = UserDto.builder()
                .id((user.getId()))
                .email(user.getEmail())
                .userType(user.getUserType())
                .fullName(user.getUsername())
                .phone(user.getPhone())
                .build();

        TokenDto tokenDto = TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return AuthorizationDto.builder()
                .token(tokenDto)
                .user(userDto)
                .build();
    }

    @Override
    public TokenDto refresh(String tokenDto) {
        String username = jwtService.extractUsername(tokenDto);
        if (username != null && jwtService.isTokenOfType(tokenDto, "refresh")) {
            User user = this.userService.findByEmail(username)
                    .orElseThrow(() -> new NotFoundException(NOT_FOUND));
            if (user.getRefreshToken() == null || !user.getRefreshToken().equals(tokenDto)) {
                throw new NotFoundException(TOKEN_NOT_FOUND);
            }
            if (jwtService.isTokenValid(tokenDto, (UserDetails) user)) {
                String accessToken = jwtService.generateAccessToken(user);
                return TokenDto.builder()
                        .accessToken(accessToken)
                        .refreshToken(tokenDto)
                        .build();
            }
        }
        throw new UnauthorizedException(INVALID_REFRESH_TOKEN);
    }

    @Override
    public void logout(String token) {
        String userEmail = jwtService.extractUsername(token);
        if (userEmail != null && jwtService.isTokenOfType(token, "access")) {
            User user = userService.findByEmail(userEmail)
                    .orElseThrow(() -> new NotFoundException(NOT_FOUND));
            user.setRefreshToken(null);
            userService.save(user);
        } else {
            throw new UnauthorizedException(INVALID_ACCESS_TOKEN);
        }
    }
}




