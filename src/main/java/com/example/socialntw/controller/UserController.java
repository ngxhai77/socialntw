package com.example.socialntw.controller;

import com.example.socialntw.dto.PasswordUpdateDto;
import com.example.socialntw.dto.UserDto;
import com.example.socialntw.dto.UserUpdateDto;
import com.example.socialntw.entity.User;
import com.example.socialntw.parent.ApiResponse;
import com.example.socialntw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.socialntw.common.constant.AppConstant.ADMIN_PERMISSION;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize(ADMIN_PERMISSION)
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.addUser(userDto);
        ApiResponse apiResponse = new ApiResponse(HttpStatus.CREATED.value(), createdUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<ApiResponse> allUser() {
        List<UserDto> users = userService.getAllUsers();
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.value(),users);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/findById/{userId}")
    public ResponseEntity<ApiResponse> findUserById(@PathVariable Integer userId) {
        UserDto user = userService.getUserById(userId);
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.value(), user);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateUser(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UserUpdateDto userUpdateDto) {
        User user = (User) userDetails;
        Integer userId = user.getId();
        userService.updateUser(userId, userUpdateDto);
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<ApiResponse> changePassword(@AuthenticationPrincipal UserDetails userDetails, @RequestBody PasswordUpdateDto passwordUpdateDto) {
        User user = (User) userDetails;
        Integer userId = user.getId();
        userService.updatePassword(userId, passwordUpdateDto);
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PreAuthorize(ADMIN_PERMISSION)
    @PostMapping("/{userId}/block")
    public ResponseEntity<ApiResponse> blockUser(@PathVariable Integer userId) {
        userService.blockUser(userId);
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PreAuthorize(ADMIN_PERMISSION)
    @PostMapping("/{userId}/unblock")
    public ResponseEntity<ApiResponse> unBlockUser(@PathVariable Integer userId) {
        userService.unBlockUser(userId);
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

//    @DeleteMapping("/delete")
//    public ResponseEntity<ApiResponse> deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
//        User user = (User) userDetails;
//        Inet userId = user.getUserId();
//        userService.deleteUser(userId);
//        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.value());
//        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//    }
}
