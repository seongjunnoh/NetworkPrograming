package org.example.IceBreaking.repository.team;

import org.example.IceBreaking.domain.Team;

import java.util.List;

public interface TeamRepository {

    void save(Team team);

    List<Team> findAll();
}
