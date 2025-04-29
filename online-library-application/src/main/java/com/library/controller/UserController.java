package com.library.controller;

import java.util.List;

import com.library.entity.User;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String email, @RequestParam String password) {
        User loggedInUser = userService.login(email, password);
        return loggedInUser != null ? ResponseEntity.ok(loggedInUser) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/check-membership/{userId}")
    public ResponseEntity<String> checkMembershipValidity(@PathVariable Long userId) {
        boolean isValid = userService.checkMembershipValidity(userId);
        return isValid ? ResponseEntity.ok("Membership is valid") : ResponseEntity.badRequest().body("Membership expired");
    }
}