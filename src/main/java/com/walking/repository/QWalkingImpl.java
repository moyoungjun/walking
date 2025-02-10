package com.walking.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walking.dto.request.WalkSaveRequest;
import com.walking.dto.response.WalkResponse;
import com.walking.entity.QFriend;
import com.walking.entity.QUser;
import com.walking.entity.QWalk;
import com.walking.service.CommonService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class QWalkingImpl implements QWalkingRepository{
    private final JPAQueryFactory jpaQueryFactory;

    private static final QUser user = QUser.user;
    private static final QWalk walk = QWalk.walk;
    private static final QFriend friend = QFriend.friend;

    public QWalkingImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }


    public List<WalkResponse> findWalksByUserSeqAndPeriodType(Long userSeq, LocalDate startDate, LocalDate endDate) {
        return jpaQueryFactory
                .select(Projections.fields(WalkResponse.class,
                        walk.userSeq,
                        user.userName,
                        walk.steps.sum().as("steps"),  // sum 처리
                        walk.totalDistance.sum().as("totalDistance")  // sum 처리
                ))
                .from(walk)
                .join(user).on(walk.userSeq.eq(user.userSeq))
                .where(
                        // 친구 코드가 주어진 사용자 친구 코드에 포함된 경우 또는 동일 사용자일 경우
                        user.friendCode.in(
                                        JPAExpressions.select(friend.receiverFriendCode)
                                                .from(friend)
                                                .join(user).on(friend.senderFriendCode.eq(user.friendCode))
                                                .where(user.userSeq.eq(userSeq))
                                )
                                .or(user.userSeq.eq(userSeq))  // userSeq와 매핑
                                .and(walk.regDatetime.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59)))  // 날짜 범위 조건
                )
                .groupBy(walk.userSeq, user.userName)
                .orderBy(walk.steps.sum().desc()) // 총 걸음 수 기준 내림차순 정렬
                .fetch();
        }


    public WalkResponse findByUserSeqAndDate(Long userSeq, LocalDate day) {
        return jpaQueryFactory
                .select(Projections.fields(WalkResponse.class,
                        walk.userSeq.as("userSeq"),
                        user.userName.as("userName"),
                        walk.steps.as("steps"),
                        walk.totalDistance.as("totalDistance"),
                        walk.walkDay.as("walkDay"),
                        walk.regDatetime.as("regDatetime") // LocalDateTime 그대로 사용
                ))
                .from(walk)
                .join(user).on(walk.userSeq.eq(user.userSeq))
                .where(walk.userSeq.eq(userSeq)
                        .and(walk.walkDay.eq(day)))  // LocalDate로 직접 비교
                .fetchFirst();
    }

    @Transactional
    public boolean updateWalk(Long userSeq, WalkSaveRequest walkSaveRequest) {
        long result = jpaQueryFactory.update(walk)
                .set(walk.steps, walk.steps.add(walkSaveRequest.getSteps()))  // 덧셈 연산
                .set(walk.totalDistance, walk.totalDistance.add(CommonService.calculateDistanceInKm(walkSaveRequest.getSteps()))) // 덧셈 연산
                .where(walk.userSeq.eq(userSeq)
                        .and(walk.walkDay.year().eq(walkSaveRequest.getDay().getYear()))
                        .and(walk.walkDay.month().eq(walkSaveRequest.getDay().getMonthValue()))
                        .and(walk.walkDay.dayOfMonth().eq(walkSaveRequest.getDay().getDayOfMonth()))
                )  // 당일 날짜로 비교
                .execute();
        return result > 0;
    }

}
