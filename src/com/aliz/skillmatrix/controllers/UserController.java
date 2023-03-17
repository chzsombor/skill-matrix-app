package com.aliz.skillmatrix.controllers;

import com.aliz.skillmatrix.model.User;
import com.aliz.skillmatrix.services.UserService;
import com.aliz.skillmatrix.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ApiResponse<List<User>> findAll() {
        return new ApiResponse<>(HttpStatus.OK.value(), userService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<User> findById(@PathVariable Long id) {
        User user = userService.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return new ApiResponse<>(HttpStatus.OK.value(), user);
    }

    @PostMapping
    public ApiResponse<User> create(@RequestBody User user) {
        return new ApiResponse<>(HttpStatus.CREATED.value(), userService.create(user));
    }

    @PutMapping("/{id}")
    public ApiResponse<User> update(@PathVariable Long id, @RequestBody User user) {
        return new ApiResponse<>(HttpStatus.OK.value(), userService.update(id, user));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully");
    }
}
