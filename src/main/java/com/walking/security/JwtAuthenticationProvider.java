package com.walking.security;

import com.walking.common.BaseException;
import com.walking.entity.User;
import com.walking.repository.UserRepository;
import com.walking.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import static com.walking.common.ErrorCode.INVALID_AUTH_TOKEN;
import static com.walking.common.ErrorCode.USER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getCredentials().toString();
        String userId = jwtService.getUserId(token);
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new BaseException(USER_NOT_FOUND));
        
        //토큰 검증 및 사용자 존재 확인
        if(!jwtService.validateToken(token) || user == null) {
            throw new BaseException(INVALID_AUTH_TOKEN);
        }
        return new JwtAuthenticationToken(user, token, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
