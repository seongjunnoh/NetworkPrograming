package org.example.IceBreaking.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TeamInterestsDto {

    private String interestValue;       // 대화주제 키워드 value값
    private int count;                  // 팀플방의 user들중 해당 대화주제 키워드 선택 수
}
