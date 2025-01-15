package com.example.walking;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walking.WalkingApplication;
import com.walking.entity.QUser;
import com.walking.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = WalkingApplication.class)
@Transactional
public class QueryDslTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JPAQueryFactory queryFactory;

    @Test
    void testFindByUserId() {
        // 테스트 데이터를 먼저 삽입합니다.
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER")); // 권한 설정

        User user = User.builder()
                .userId("testUser")
                .userName("test")
                .password("password123")
                .authorities(authorities)
                .build();
        entityManager.persist(user);  // user를 영속화합니다.

        // Querydsl을 사용하여 User를 찾습니다.
        QUser qUser = QUser.user;  // QUser는 Querydsl로 생성된 Q 클래스입니다.
        User foundUser = queryFactory
                .selectFrom(qUser)
                .where(qUser.userId.eq("testUser"))
                .fetchOne();

        // User가 제대로 조회되었는지 확인합니다.
        assertThat(foundUser).isNotNull();
        assertThat(foundUser != null ? foundUser.getUserId() : null).isEqualTo("testUser");
        assert foundUser != null;
        assertThat(foundUser.getUsername()).isEqualTo("test");
    }
}