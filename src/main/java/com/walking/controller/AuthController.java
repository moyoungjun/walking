package com.walking.controller;

import com.walking.dto.request.AuthRequest;
import com.walking.dto.response.AuthResponse;
import com.walking.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public AuthResponse authorize(@RequestBody AuthRequest authRequest) {
        return authService.authorize(authRequest);
    }
}
