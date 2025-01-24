package com.walking.dto.response;

import com.walking.enums.PeriodType;

import java.time.LocalDate;

public record WalkResponse(
        Long userSeq,
        String userName,
        LocalDate walkDate,
        Integer steps,
        PeriodType periodType,
        Double totalDistance
) {

}
