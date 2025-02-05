package com.walking.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class WalkRequest {
        Long userSeq;
        Integer steps;
        Double totalDistance;
        Date startDate;
        Date endDate;
}
