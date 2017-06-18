package com.example.rest.rest_repository;

import com.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestUserRepository extends JpaRepository<User, String> {
}