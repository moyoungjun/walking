package com.walking.controller;

import com.walking.common.SuccessResponse;
import com.walking.dto.request.WalkSaveRequest;
import com.walking.dto.response.WalkResponse;
import com.walking.entity.User;
import com.walking.service.WalkService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/walk")
public class WalkController {
    private final WalkService walkService;

    // 본인과 친구 들의 걸음 수
    @GetMapping("/list")
    public ResponseEntity<SuccessResponse> getFriendWalkList(@AuthenticationPrincipal User user, @RequestParam("startDate") LocalDate startDate,@RequestParam("endDate") LocalDate endDate) {
        List<WalkResponse> walks = walkService.getFriendWalkList(user.getUserSeq(), startDate, endDate);
        return SuccessResponse.toSuccessResponseEntity(true, walks);
    }

    @PostMapping("/post")
    public ResponseEntity<SuccessResponse> insertMyWalkInfo(@AuthenticationPrincipal User user, @RequestBody WalkSaveRequest walkSaveRequest) {
        boolean result = walkService.insertMyWalkInfo(user.getUserSeq(), walkSaveRequest);
        return SuccessResponse.toSuccessResponseEntity(result, "");
    }
}
