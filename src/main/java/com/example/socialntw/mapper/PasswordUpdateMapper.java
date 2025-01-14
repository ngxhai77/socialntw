package com.example.socialntw.mapper;

import com.example.socialntw.dto.PasswordUpdateDto;
import com.example.socialntw.entity.User;

public class PasswordUpdateMapper {
    public static PasswordUpdateDto toDto(User user) {
        PasswordUpdateDto passwordUpdateDto = new PasswordUpdateDto();
        passwordUpdateDto.setNewPassword(user.getPasswordHash());
        return passwordUpdateDto;
    }

    public static User toCreateEntity(PasswordUpdateDto passwordUpdateDto) {
        User user = new User();
        user.setPasswordHash(passwordUpdateDto.getNewPassword());
        return user;
    }

    public static User toUpdateEntity(User user, PasswordUpdateDto passwordUpdateDto) {
        user.setPasswordHash(passwordUpdateDto.getNewPassword());
        return user;
    }
}
