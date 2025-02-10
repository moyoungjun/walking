package com.walking.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class WalkResponse {

    private Long userSeq;
    private String userName;
    private Integer steps;
    private BigDecimal totalDistance;
    private LocalDate walkDay;
    private LocalDateTime regDatetime;

}