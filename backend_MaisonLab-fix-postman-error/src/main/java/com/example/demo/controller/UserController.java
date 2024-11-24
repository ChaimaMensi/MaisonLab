package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint pour l'inscription (sign up)
    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        try {
            User registeredUser = userService.signUp(user);
            return ResponseEntity.ok(registeredUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Endpoint pour la connexion (sign in)
    @PostMapping("/signin")
    public ResponseEntity<User> signIn(@RequestParam String username, @RequestParam String password) {
        try {
            User authenticatedUser = userService.signIn(username, password);
            return ResponseEntity.ok(authenticatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
