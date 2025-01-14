package com.example.socialntw.controller;

import com.example.socialntw.dto.AuthorizationDto;
import com.example.socialntw.dto.LoginDto;
import com.example.socialntw.dto.TokenDto;
import com.example.socialntw.dto.UserDto;
import com.example.socialntw.parent.ApiResponse;
import com.example.socialntw.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.socialntw.common.constant.MessageConstant.LOGOUT_SUCCESS;
import static com.example.socialntw.common.constant.MessageConstant.TOKEN_REFRESHED_SUCCESSFULL;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody UserDto userDto) {
        UserDto createdUser = authService.registerNewUser(userDto);
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.value(), createdUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginDto loginDto) {
        AuthorizationDto tokenDto = authService.login(loginDto);
        ApiResponse apiResponse = new ApiResponse(HttpStatus.ACCEPTED.value(), tokenDto);
        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        if (refreshToken.startsWith("Bearer ")) {
            refreshToken = refreshToken.substring(7);
        }
        TokenDto tokenDto = authService.refresh(refreshToken);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK.value(), TOKEN_REFRESHED_SUCCESSFULL, tokenDto), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logout(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer", "");
        authService.logout(token);
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.value());
        apiResponse.setMessage(LOGOUT_SUCCESS);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
