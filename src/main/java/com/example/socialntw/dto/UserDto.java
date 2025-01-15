package com.example.socialntw.dto;

import com.example.socialntw.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String password;
    private String confirmPassword;
    private String email;
    private String phone;
    private String userName;
    private UserType userType;
}
