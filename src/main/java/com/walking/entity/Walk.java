package com.walking.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EnableJpaRepositories
public class Walk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "walk_seq", nullable = false)
    private Long walkSeq;

    @Column(name = "user_seq", nullable = false)
    private Long userSeq;

    @Column(name = "steps", nullable = false)
    private Integer steps = 0;

    @Column(name = "total_distance", precision = 10, scale = 1)
    private BigDecimal totalDistance = new BigDecimal("0.0");

    @Column(name = "walk_day")
    private LocalDate walkDay;

    @Comment("등록 일시")
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime regDatetime;

    @Column(name = "mod_datetime", nullable = true)
    private LocalDateTime modDatetime;

    @Column(name = "friend_code", length = 10, nullable = true)
    private String friendCode;

}
