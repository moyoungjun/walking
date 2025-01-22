package com.walking.controller;

import com.walking.dto.response.WalkResponse;
import com.walking.enums.PeriodType;
import com.walking.service.WalkService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/walk")
public class WalkController {
    private final WalkService walkService;

    // 본인과 친구 들의 걸음 수
    @GetMapping("/list")
    public List<WalkResponse> getFriendWalkList(@RequestParam Long userSeq, @RequestParam PeriodType periodType) {
        return walkService.getFriendWalkList(userSeq, periodType);
    }
}
