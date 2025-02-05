package com.walking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class CommonService {
    /*
    걸음 수에 따른 km 계산
    */

    public static BigDecimal calculateDistanceInKm(int steps) {
        BigDecimal stepLength = new BigDecimal("0.8");  // 한 걸음의 평균 길이
        BigDecimal stepsBD = new BigDecimal(steps);
        BigDecimal distanceInMeters = stepsBD.multiply(stepLength);  // 총 거리 계산 (미터)

        // 미터를 킬로미터로 변환하고, 소수점 첫째 자리에서 반올림
        return distanceInMeters.divide(new BigDecimal("1000"), 2, RoundingMode.HALF_UP);
    }
}
