package org.example.IceBreaking.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Team {

    private String teamName;

    public Team(String teamName) {
        this.teamName = teamName;
    }
}
