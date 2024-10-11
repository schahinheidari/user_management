package com.UserManager.controller;

import com.UserManager.dao.UserRepository;
import com.UserManager.model.entites.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/v1")
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping("list")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return userRepository.findAll();
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userRepository.save(user);
    }


}
