package com.walking.service;

import com.walking.dto.response.WalkResponse;
import com.walking.enums.PeriodType;
import com.walking.repository.QWalkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalkService {
    private final QWalkingRepository qWalkingRepository;

    public List<WalkResponse> getFriendWalkList(Long userSeq, PeriodType periodType) {
        return qWalkingRepository.findWalksByUserSeqAndPeriodType(userSeq, periodType);
    }

}