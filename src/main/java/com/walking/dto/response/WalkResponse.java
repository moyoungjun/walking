package com.walking.dto.response;

import java.time.LocalDate;

public record WalkResponse(
        Long userSeq,
        String userName,
        LocalDate walkDate,
        Integer steps,
        Double totalDistance
) {

}
