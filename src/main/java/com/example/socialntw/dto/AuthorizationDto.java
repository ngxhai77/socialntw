package com.example.socialntw.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorizationDto {
    private TokenDto token;
    private UserDto user;
}
