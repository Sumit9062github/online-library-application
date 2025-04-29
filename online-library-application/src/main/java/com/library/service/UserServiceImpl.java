package com.library.service;

import com.library.entity.User;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        // Calculate the membership validity date based on the membership months
        Date currentDate = new Date();
        long millisecondsInMonth = 1000L * 60 * 60 * 24 * 30;
        Date membershipValidTill = new Date(currentDate.getTime() + user.getMembershipMonths() * millisecondsInMonth);
        user.setMembershipValidTill(membershipValidTill);

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean checkMembershipValidity(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Date currentDate = new Date();
            return currentDate.before(user.getMembershipValidTill()); // Check if current date is before membership expiry
        }
        return false;
    }

    @Override
    public User login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // Return null if login fails
    }
}