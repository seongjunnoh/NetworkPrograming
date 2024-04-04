package org.example.icebraking.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {

    private String userId;
    private String password;
    private String name;
    private String department;      // 학과
    private String studentId;       // 학번
}
