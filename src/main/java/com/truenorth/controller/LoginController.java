package com.truenorth.controller;

import com.truenorth.dto.LoginForm;
import com.truenorth.entity.User;
import com.truenorth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm) {
        // Perform login logic here
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        // Validate username and password
        if (isValidCredentials(username, password)) {
            // Successful login
            return ResponseEntity.ok("Login successful");
        } else {
            // Invalid credentials
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    private boolean isValidCredentials(String username, String password) {
        Optional<User> userOptional = userService.getByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (password.equals(user.getPassword()))
                return true;
        }
        return false;
    }
}
