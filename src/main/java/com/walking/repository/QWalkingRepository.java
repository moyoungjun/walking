package com.walking.repository;

import com.walking.dto.request.WalkSaveRequest;
import com.walking.dto.response.WalkResponse;

import java.time.LocalDate;
import java.util.List;

public interface QWalkingRepository{
    List<WalkResponse> findWalksByUserSeqAndPeriodType(Long userSeq, LocalDate startDate, LocalDate endDate);

    WalkResponse findByUserSeqAndDate(Long userSeq);

    boolean updateWalk(Long userSeq, WalkSaveRequest walkSaveRequest);
}
