package org.example.IceBreaking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class User {

    private static int nextId = 1;

    private int id;                 // User 객체 고유의 id
    private String userId;          // 로그인시 필요한 userId
    private String password;
    private String name;
    private String department;      // 학과
    private String studentId;       // 학번
    private String[] interests;     // 관심사 키워드 list

    public void setId() {
        this.id = nextId++;
    }

    public void setIdForEdit(int id) {
        this.id = id;
    }

    public boolean matchPassword(String password) {
        if (password == null) {
            return false;
        }
        return this.password.equals(password);
    }
}
