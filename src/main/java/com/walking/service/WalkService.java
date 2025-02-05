package com.walking.service;

import com.walking.common.BaseException;
import com.walking.dto.request.WalkSaveRequest;
import com.walking.dto.response.WalkResponse;
import com.walking.entity.Walk;
import com.walking.repository.QWalkingRepository;
import com.walking.repository.WalkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.walking.common.ErrorCode.INSERT_FAIL;

@Service
@RequiredArgsConstructor
public class WalkService {

    private final WalkRepository walkRepository;
    private final QWalkingRepository qWalkingRepository;

    public List<WalkResponse> getFriendWalkList(Long userSeq, LocalDate startDate, LocalDate endDate) {
        return qWalkingRepository.findWalksByUserSeqAndPeriodType(userSeq, startDate, endDate);
    }

    public boolean insertMyWalkInfo(Long userSeq, WalkSaveRequest walkSaveRequest) {
        // 사용자와 날짜에 해당하는 Walk 데이터를 찾기
        WalkResponse existingWalk = qWalkingRepository.findByUserSeqAndDate(userSeq);

        if (existingWalk != null) {
            // 기존 걸음수에 추가
            return qWalkingRepository.updateWalk(userSeq, walkSaveRequest);
        } else {
            // 오늘 날짜에 새로운 Walk 데이터 생성
            return insertWalk(userSeq, walkSaveRequest);
        }
    }

    public boolean insertWalk(Long userSeq, WalkSaveRequest walkSaveRequest) {
        try {
            Walk newWalk = new Walk();
            newWalk.setUserSeq(userSeq);
            newWalk.setSteps(walkSaveRequest.getSteps());
            newWalk.setTotalDistance(CommonService.calculateDistanceInKm(walkSaveRequest.getSteps()));
            walkRepository.save(newWalk);
            return true;  // 성공 시 true 반환
        } catch (Exception e) {
            throw new BaseException(INSERT_FAIL);
        }
    }

}