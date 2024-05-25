package org.example.IceBreaking.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {
    private int teamId;
    private String userId;
    private String userName;
    private String message;
}
