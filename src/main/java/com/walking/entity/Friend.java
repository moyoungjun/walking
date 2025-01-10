package com.walking.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EnableJpaRepositories
@Table(
        name = "friend",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"senderFriendCode", "receiverFriendCode"})
        }
)
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendSeq;

    @Comment("친구 요청한 유저의 친구 고유 코드")
    private String senderFriendCode;

    @Comment("친구 요청을 받은 유저의 친구 고유 코드")
    private String receiverFriendCode;

}
