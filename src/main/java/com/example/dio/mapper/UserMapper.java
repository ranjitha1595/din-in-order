package com.example.dio.mapper;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public void mapToUserEntity(UserRequest userRequest, User user) { // for update
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPhno(userRequest.getPhno());
    }

    public void mapToUserEntity(RegistrationRequest registrationRequest, User user) {
        user.setUsername(registrationRequest.getUsername());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(registrationRequest.getPassword());
        user.setPhno(registrationRequest.getPhno());
        user.setRole(registrationRequest.getRole());
    }

    public void userRequest(UserRequest userRequest, User user) { //for update
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPhno(userRequest.getPhno());
    }

    public UserResponse mapToUserResponse(User user) {
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

    public void mapToNewUser(UserRequest source, User target) { // for update
        target.setUsername(source.getUsername());
        target.setEmail(source.getEmail());
        target.setPhno(source.getPhno());

    }


}
