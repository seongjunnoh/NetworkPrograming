package org.example.IceBreaking.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInterestDto {
    private int teamId;
    private String[] interests;
}
