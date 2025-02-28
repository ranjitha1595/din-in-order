package com.example.dio.service.impl;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.enums.UserRole;
import com.example.dio.exception.UserNotFoundByIdException;
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

    private void mapToUserEntity(UserRequest userRequest, User user) { // for update
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPhno(userRequest.getPhno());
    }

    private void mapToUserEntity(RegistrationRequest registrationRequest, User user) {
        user.setUsername(registrationRequest.getUsername());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(registrationRequest.getPassword());
        user.setPhno(registrationRequest.getPhno());
        user.setRole(registrationRequest.getRole());
    }

    private void userRequest(UserRequest userRequest, User user) { //for update
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPhno(userRequest.getPhno());
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .lastModifiedAt(user.getLastModifiedAt())
                .build();
    }

//    private void mapToNewUser(User source, User target) {
//        target.setUsername(source.getUsername());
//        target.setEmail(source.getEmail());
//        target.setRole(source.getRole());
//        target.setPhno(source.getPhno());
//        target.setPassword(source.getPassword());
//    }

    private void mapToNewUser(UserRequest source, User target) { // for update
        target.setUsername(source.getUsername());
        target.setEmail(source.getEmail());
        target.setPhno(source.getPhno());

    }

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
        this.mapToUserEntity(registrationRequest,user);
        userRepository.save(user);
        return this.mapToUserResponse(user);
    }

    @Override
    public UserResponse findUserById(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundByIdException("Failed to find user, user not found by id"));
        return this.mapToUserResponse(user);
    }

    @Override
    public UserResponse updateUserById(UserRequest userRequest, long userId) {
        User exUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundByIdException("User not found"));
        this.mapToUserEntity(userRequest,exUser);
        userRepository.save(exUser);
                return  this.mapToUserResponse(exUser);

    }



}
