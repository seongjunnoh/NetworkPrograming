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

    public User(String userId, String password, String name, String department, String studentId) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.department = department;
        this.studentId = studentId;
    }

    public boolean matchPassword(String password) {
        if (password == null) {
            return false;
        }

        return this.password.equals(password);
    }
}
