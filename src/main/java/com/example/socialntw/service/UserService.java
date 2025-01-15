package com.example.socialntw.service;

import com.example.socialntw.dto.PasswordUpdateDto;
import com.example.socialntw.dto.UserDto;
import com.example.socialntw.dto.UserUpdateDto;
import com.example.socialntw.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto addUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto getUserById(Integer userId);


//    void deleteUser(String userId);

    void updateUser(Integer userId, UserUpdateDto userUpdateDto);

    boolean existsByEmail(String email);

    boolean validPhoneNumber(String phoneNumber);

    User save(User user);

    Optional<User> findByEmail(String email);

    boolean validEmail(String email);

    Optional<User> findById(Integer userId);

    void blockUser(Integer userId);

    void unBlockUser(Integer userId);

    boolean validFullName(String str);

    User saveUser(User user);

    Optional<User> findUserByRefreshToken(String refreshToken);

    void updatePassword(Integer id, PasswordUpdateDto passwordUpdateDto);
}
