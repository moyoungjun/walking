package com.walking.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walking.dto.response.WalkResponse;
import com.walking.entity.QFriend;
import com.walking.entity.QUser;
import com.walking.entity.QWalk;
import com.walking.enums.PeriodType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QWalkingImpl implements QWalkingRepository{
    private final JPAQueryFactory jpaQueryFactory;

    public QWalkingImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<WalkResponse> findWalksByUserSeqAndPeriodType(Long userSeq, PeriodType periodType) {
        QUser user = QUser.user;
        QWalk walk = QWalk.walk;
        QFriend friend = QFriend.friend;

        return jpaQueryFactory
                .select(Projections.constructor(WalkResponse.class,
                        walk.userSeq,
                        user.userName,
                        walk.walkDate,
                        walk.steps,
                        walk.periodType,
                        walk.totalDistance))
                .from(walk)
                .join(user).on(walk.userSeq.eq(user.userSeq))
                .where(
                        // 서브쿼리와 조건을 명확하게 그룹화
                        user.friendCode.in(
                                        JPAExpressions.select(friend.receiverFriendCode)
                                                .from(friend)
                                                .join(user).on(friend.senderFriendCode.eq(user.friendCode))
                                                .where(user.userSeq.eq(userSeq))
                                )
                                .or(user.userSeq.eq(userSeq))  // userSeq와 매핑
                                .and(walk.periodType.eq(periodType)) // periodType 필터링
                )
                .orderBy(walk.steps.desc())
                .fetch();
    }
}
