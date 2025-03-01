package com.example.dio.repository;

import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    Optional<UserResponse> findById(long userId);
}
