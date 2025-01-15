package com.example.socialntw.service.implement;

import com.example.socialntw.dto.PasswordUpdateDto;
import com.example.socialntw.dto.UserDto;
import com.example.socialntw.dto.UserUpdateDto;
import com.example.socialntw.entity.User;
import com.example.socialntw.exception.BadRequestException;
import com.example.socialntw.exception.NotFoundException;
import com.example.socialntw.exception.UnauthorizedException;
import com.example.socialntw.mapper.PasswordUpdateMapper;
import com.example.socialntw.mapper.UserMapper;
import com.example.socialntw.repository.UserRepository;
import com.example.socialntw.service.JwtService;
import com.example.socialntw.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.example.socialntw.common.constant.AppConstant.*;
import static com.example.socialntw.common.constant.MessageConstant.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;



    @Override
    public UserDto addUser(UserDto userDto) {
        if (userDto.getPassword().trim().isEmpty() || userDto.getEmail().trim().isEmpty() || userDto.getUserName().isEmpty()) {
            throw new BadRequestException(FIELD_INVALID);
        }
        if (!validEmail(userDto.getEmail())) {
            throw new BadRequestException(EMAIL_INVALID);
        }
        if (!validFullName(userDto.getUserName())) {
            throw new BadRequestException(NAME_INVALID);
        }
        if(!validPhoneNumber(userDto.getPhone())){
            throw new BadRequestException(PHONE_INVALID);
        }
        if (!userDto.getPassword().matches(userDto.getConfirmPassword())) {
            throw new BadRequestException(PASSWORD_DONT_MATCH);
        }
        userDto.setPassword(userDto.getPassword().trim());
        userDto.setConfirmPassword(userDto.getConfirmPassword().trim());
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new BadRequestException(USER_EXISTED);
        } else {
            User user = UserMapper.toCreateEntity(userDto);
            String encodedPassword = passwordEncoder.encode(userDto.getPassword());
            user.setPasswordHash(encodedPassword);
            user.setIsActive(true);
            userRepository.save(user);
            return UserMapper.toDto(user);
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        Collection<? extends GrantedAuthority> authorities = jwtService.getCurrentUserType();
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            List<User> user = userRepository.findAll();
            return UserMapper.toDtos(user);
        } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_CREATOR")) || authorities.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            List<User> user = userRepository.findByIsActiveTrue();
            return UserMapper.toDtos(user);
        } else {
            throw new UnauthorizedException(USERTYPE_INVALID);
        }

    }

    //chua check
    @Override
    public UserDto getUserById(Integer userId) {
        Collection<? extends GrantedAuthority> authorities = jwtService.getCurrentUserType();
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
            return UserMapper.toDto(user);
        } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_CREATOR"))
                || authorities.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            User user = userRepository.findByIdAndIsActiveTrue(userId)
                    .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
            return UserMapper.toDto(user);
        } else {
            throw new UnauthorizedException(USERTYPE_INVALID);
        }
    }


    @Override
    public void blockUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        user.setIsActive(false);
        userRepository.save(user);
    }

    @Override
    public void unBlockUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        user.setIsActive(true);
        userRepository.save(user);
    }



    @Transactional
    @Override
    public void updateUser(Integer userId, UserUpdateDto userUpdateDto) {
        if (userUpdateDto.getEmail().isEmpty() || userUpdateDto.getUserName().isEmpty()) {
            throw new BadRequestException(FIELD_INVALID);
        }
        if (!validFullName(userUpdateDto.getUserName())) {
            throw new BadRequestException(NAME_INVALID);
        }
        if (!validEmail(userUpdateDto.getEmail())) {
            throw new BadRequestException(EMAIL_INVALID);
        }
        if (!validPhoneNumber(userUpdateDto.getPhone())){
            throw new BadRequestException(PHONE_INVALID);
        }
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!user.getEmail().equals(userUpdateDto.getEmail())) {
                if (userRepository.existsByEmailAndIdNot(userUpdateDto.getEmail(), user.getId())) {
                    throw new BadRequestException(USER_EXISTED);
                }
            }
            user.setUsername(userUpdateDto.getUserName());
            user.setPhone(userUpdateDto.getPhone());
            user.setEmail(userUpdateDto.getEmail());

            userRepository.save(user);
        } else {
            throw new BadRequestException(ID_INVALID);
        }
    }

    @Override
    public void updatePassword(Integer userId, PasswordUpdateDto passwordUpdateDto) {
        if (passwordUpdateDto.getOldPassword().isEmpty() || passwordUpdateDto.getNewPassword().trim().isEmpty() || passwordUpdateDto.getConfirmPassword().isEmpty()) {
            throw new BadRequestException(FIELD_INVALID);
        }
        if (passwordUpdateDto.getNewPassword().matches(passwordUpdateDto.getOldPassword())) {
            throw new BadRequestException(NEW_PASSWORD_CANNOT_BE_OLD_PASSWORD);
        }
        if (!passwordUpdateDto.getNewPassword().matches(passwordUpdateDto.getConfirmPassword())) {
            throw new BadRequestException(PASSWORD_DONT_MATCH);
        }
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!passwordEncoder.matches(passwordUpdateDto.getOldPassword(), user.getPasswordHash())) {
                throw new BadRequestException(WRONG_OlD_PASSWORD);
            }
            User userSaved = PasswordUpdateMapper.toUpdateEntity(user, passwordUpdateDto);
            String encodedPassword = passwordEncoder.encode(passwordUpdateDto.getNewPassword());
            user.setPasswordHash(encodedPassword);
            userRepository.save(userSaved);
        } else {
            throw new BadRequestException(ID_INVALID);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean validEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    @Override
    public Optional<User> findById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public boolean validPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return true;
        }
        return PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches();
    }

    @Override
    public boolean validFullName(String str) {
        return str != null && NAME_PATTERN.matcher(str).find();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserByRefreshToken(String refreshToken) {
        return userRepository.findByRefreshToken(refreshToken);
    }


}
