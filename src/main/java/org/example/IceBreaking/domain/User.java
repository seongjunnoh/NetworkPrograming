package org.example.IceBreaking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private String userId;          // 로그인시 필요한 userId
    private String password;
    private String name;
    private String department;      // 학과
    private String studentId;       // 학번
    private String[] interests;     // 관심사 키워드 list
    private boolean editInfoFlag;   // user의 개인정보 수정 유/무 판단 -> memoryQuestionRepo에서 사용

    public boolean matchPassword(String password) {
        if (password == null) {
            return false;
        }
        return this.password.equals(password);
    }
}
