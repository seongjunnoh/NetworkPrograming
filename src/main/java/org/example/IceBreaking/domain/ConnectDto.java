package org.example.IceBreaking.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConnectDto {
    private int teamId;
    private String userName;
    private String message;
}
