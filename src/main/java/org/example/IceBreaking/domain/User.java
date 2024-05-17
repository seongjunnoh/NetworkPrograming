package org.example.IceBreaking.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class User {

    private static int nextId = 1;

    private int id;                 // User 객체 고유의 id
    private String userId;
    private String password;
    private String name;
    private String department;      // 학과
    private String studentId;       // 학번
    private String interest;        // 관심사(상위 키워드)
    private String subInterest;     // 관심사(하위 키워드)

    public void setId() {
        this.id = nextId++;
    }

    public boolean matchPassword(String password) {
        if (password == null) {
            return false;
        }
        return this.password.equals(password);
    }
}
