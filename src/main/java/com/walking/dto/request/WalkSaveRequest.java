package com.walking.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class WalkSaveRequest{
    private Integer steps;

    private LocalDate day;

}
