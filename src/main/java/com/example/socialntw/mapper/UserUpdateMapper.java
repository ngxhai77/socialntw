package com.example.socialntw.mapper;

import com.example.socialntw.dto.UserUpdateDto;
import com.example.socialntw.entity.User;

public class UserUpdateMapper {
    public static UserUpdateDto toDto(User user) {
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setPhone(user.getPhone());
        userUpdateDto.setUserName(user.getUsername());
        return userUpdateDto;
    }

    public static User toCreateEntity(UserUpdateDto userUpdateDto) {
        User user = new User();
        user.setPhone(userUpdateDto.getPhone());
        user.setUsername(userUpdateDto.getUserName());
        return user;
    }

    public static User toUpdateEntity(User user, UserUpdateDto userUpdateDto) {
        user.setPhone(userUpdateDto.getPhone());
        user.setUsername(userUpdateDto.getUserName());
        return user;
    }
}
