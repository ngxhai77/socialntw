package com.example.socialntw.mapper;

import com.example.socialntw.dto.UserDto;
import com.example.socialntw.entity.User;

import java.util.List;
import java.util.stream.Collectors;


public class UserMapper {
    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPassword(user.getPasswordHash());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setFullName(user.getUsername());
        userDto.setUserType(user.getUserType());
        return userDto;
    }

    public static List<UserDto> toDtos(List<User> users) {
        if (users == null) {
            return null;
        }
        return users.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public static User toCreateEntity(UserDto userDto) {
        User user = new User();
        user.setPasswordHash(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setUsername(userDto.getFullName());
        user.setUserType(userDto.getUserType());
        return user;
    }

    public static User toUpdateEntity(User user, UserDto userDto) {
        user.setPasswordHash(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setUsername(userDto.getFullName());
        user.setUserType(userDto.getUserType());
        return user;
    }

}
