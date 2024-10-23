package com.walking.service;

import com.walking.dto.request.AccountRequest;
import com.walking.dto.response.AccountResponse;
import com.walking.dto.response.UserResponse;
import com.walking.entity.User;
import com.walking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserResponse getUser(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(Exception::new);

        return new UserResponse(user.getUserId(), user.getUsername());
    }

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        System.out.println("this");
        return users;
    }

    public AccountResponse CreationUser(AccountRequest accountRequest) {
        String encode = passwordEncoder.encode(accountRequest.getPassword());
        accountRequest.encodePassword(encode);
        User user = accountRequest.toEntity();
        userRepository.save(user);
        Set<String> authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        return new AccountResponse(user.getId(), user.getUsername(), user.getName(), authorities);
    }
}
