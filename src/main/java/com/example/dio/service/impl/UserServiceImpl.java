package com.example.dio.service.impl;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.enums.UserRole;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.mapper.UserMapper;
import com.example.dio.model.Admin;
import com.example.dio.model.Staff;
import com.example.dio.model.User;
import com.example.dio.repository.UserRepository;
import com.example.dio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private User createUserByRole(UserRole role){
        User user;
        switch(role){
            case ADMIN -> user = new Admin();
            case STAFF -> user = new Staff();
            default -> throw new RuntimeException("failed to register user, invalid user");
        }
        return user;
    }

    @Override
    public UserResponse registerUser(RegistrationRequest registrationRequest) {
        User user = this.createUserByRole(registrationRequest.getRole());

//        this.mapToNewUser(user,user2);
        userMapper.mapToUserEntity(registrationRequest,user);
        userRepository.save(user);
        return userMapper.mapToUserResponse(user);
    }

    @Override
    public UserResponse findUserById(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundByIdException("Failed to find user, user not found by id"));
        return userMapper.mapToUserResponse(user);
    }

    @Override
    public UserResponse updateUserById(UserRequest userRequest, long userId) {
        User exUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundByIdException("User not found"));
        userMapper.mapToUserEntity(userRequest,exUser);
        userRepository.save(exUser);
                return  userMapper.mapToUserResponse(exUser);

    }



}
