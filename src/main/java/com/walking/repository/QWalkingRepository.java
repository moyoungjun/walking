package com.walking.repository;

import com.walking.dto.response.WalkResponse;
import com.walking.enums.PeriodType;

import java.util.List;

public interface QWalkingRepository{
    List<WalkResponse> findWalksByUserSeqAndPeriodType(Long userSeq, PeriodType periodType);
}
