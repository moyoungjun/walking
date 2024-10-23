package com.walking.dto.request;

import com.walking.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccountRequest{
    String userId;
    String password;
    String passwordConfirm;
    String userName;
    public User toEntity() {
        return User.builder()
                .userId(userId)
                .password(password)
                .username(userName)
                .authorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")))
                .build();
    }
    public void encodePassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}
