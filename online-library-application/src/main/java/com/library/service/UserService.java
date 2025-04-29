package com.library.service;

import com.library.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers(); // Get all users

    User createUser(User user); // Create a new user

    User getUserById(Long id); // Get user by ID

    User updateUser(User user); // Update user details

    boolean checkMembershipValidity(Long userId); // Check membership validity

    User login(String email, String password); // Login a user
}