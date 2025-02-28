package com.example.dio.mapper;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

//@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse mapToUserResponse(User user); // automatically create the object

    void mapToUserEntity(UserRequest userRequest, @MappingTarget User user);

    void mapToUserEntity(RegistrationRequest registrationRequest, @MappingTarget User user);
}

//@MappingTarget -> it will target particular user instead of new one.