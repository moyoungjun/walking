package com.walking.service;

import com.walking.common.BaseException;
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
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static com.walking.common.ErrorCode.USER_ID_ALREADY_EXISTS;

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
        // 아이디 중복 체크
        if (userRepository.existsByUserId(accountRequest.getUserId())) {
            throw new BaseException(USER_ID_ALREADY_EXISTS);  // 아이디 중복 시 예외 처리
        }
        String encode = passwordEncoder.encode(accountRequest.getPassword());
        accountRequest.encodePassword(encode);
        User user = accountRequest.toEntity(generateUniqueFriendCode());
        userRepository.save(user);
        Set<String> authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        return new AccountResponse(user.getUserSeq(), user.getUserId(), user.getUsername(), authorities);
    }

    // 4자리 랜덤 친구 코드 생성 및 중복 방지 (알파벳 + 숫자)
    public String generateUniqueFriendCode() {
        String friendCode;
        do {
            friendCode = generateRandomCode(4);  // 랜덤 4자리 코드 생성
        } while (userRepository.existsByFriendCode(friendCode));  // User 테이블에서 중복된 친구 코드 확인

        return friendCode;
    }

    // 랜덤 8자리 코드 생성
    private String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";  // 대소문자 + 숫자
        Random random = new Random();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }
}
