package com.walking.security;

import com.walking.common.CommonUtils;
import com.walking.entity.User;
import com.walking.repository.UserRepository;
import com.walking.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    private final List<String> EXCLUDE_URL = List.of("/v3/**", "/swagger-ui/**", "/swagger-resources/**", "/swagger-ui.html", "/favicon.ico");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        System.out.println("dddddddddddddddddd" + servletPath);
        if (EXCLUDE_URL.stream().anyMatch(exclude -> CommonUtils.match(exclude, servletPath))) {
            filterChain.doFilter(request, response);
            System.out.println("여기니3");
        } else {
            System.out.println("여기니2");
            System.out.println("dddddddddddddddddd" + request);
            System.out.println("dddddddddddddddddd" + response);
            try {
                String token = resolveToken(request);
                String userId = jwtService.getUserId(token);
                User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(Exception::new);
                if (token != null && jwtService.validateToken(token)) {
                    Authentication auth = new JwtAuthenticationToken(user, token, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                filterChain.doFilter(request, response);
            }
        }
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
