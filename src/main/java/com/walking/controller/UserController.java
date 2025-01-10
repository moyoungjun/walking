package com.walking.controller;

import com.walking.dto.request.AccountRequest;
import com.walking.dto.response.AccountResponse;
import com.walking.dto.response.UserResponse;
import com.walking.entity.User;
import com.walking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserResponse test(@PathVariable Long id) throws Exception {
        return userService.getUser(id);
    }

    @GetMapping
    public List<User> test2() {
        return userService.getUsers();
    }

    @PostMapping("/register")
    public AccountResponse CreationUser(@RequestBody AccountRequest accountRequest) {
        return userService.CreationUser(accountRequest);
    }
}
