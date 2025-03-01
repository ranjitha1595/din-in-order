package com.example.dio.dto.response;

import com.example.dio.enums.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserResponse {

    private long userId;
    private String username;
    private UserRole role;
    private LocalDate createdAt;
    private LocalDate lastModifiedAt;
}
