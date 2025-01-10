package com.walking.service;


import com.walking.common.BaseException;
import com.walking.dto.request.AuthRequest;
import com.walking.dto.response.AuthResponse;
import com.walking.entity.User;
import com.walking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.walking.common.ErrorCode.INVALID_ID_PASSWORD;
import static com.walking.common.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final com.walking.service.JwtService jwtService;
    private final UserRepository userRepository;

    public AuthResponse authorize(AuthRequest authRequest) {
        User user = userRepository.findByUserId(authRequest.userId())
                .orElseThrow(() -> new BaseException(USER_NOT_FOUND));
        if(!passwordEncoder.matches(authRequest.password(), user.getPassword())) {
            throw new BaseException(INVALID_ID_PASSWORD);
        }

        String token = jwtService.createJWT(user);

        return new AuthResponse("berear",token,jwtService.getExpiration(token));
    }
}
