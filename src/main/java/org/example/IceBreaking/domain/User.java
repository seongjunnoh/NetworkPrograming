package org.example.IceBreaking.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userId;
    private String password;
    private String name;
    private String department;      // 학과
    private String studentId;       // 학번
    private String interest;        // 관심사

    public boolean matchPassword(String password) {
        if (password == null) {
            return false;
        }

        return this.password.equals(password);
    }
}
