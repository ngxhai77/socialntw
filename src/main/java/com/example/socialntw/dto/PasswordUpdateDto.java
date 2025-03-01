package com.example.socialntw.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordUpdateDto {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
