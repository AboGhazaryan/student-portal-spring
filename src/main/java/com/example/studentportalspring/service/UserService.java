package com.example.studentportalspring.service;

import com.example.studentportalspring.model.User;

import java.util.Optional;

public interface  UserService {
    void save(User user);
    Optional<User> findByUserName(String  username);
}

