package org.example.IceBreaking.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

    private String userName;            // 채팅을 입력한 user의 이름
    private String message;             // user가 작성한 message
}
