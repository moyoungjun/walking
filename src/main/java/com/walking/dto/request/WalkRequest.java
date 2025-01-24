package com.walking.dto.request;

import com.walking.enums.PeriodType;

public record WalkRequest(
        Long userSeq,
        Integer steps,
        Double totalDistance,
        PeriodType periodType

){
}
