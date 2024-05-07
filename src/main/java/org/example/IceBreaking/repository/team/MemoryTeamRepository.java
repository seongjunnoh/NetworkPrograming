package org.example.IceBreaking.repository.team;

import org.example.IceBreaking.domain.Team;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryTeamRepository implements TeamRepository{

    private final Map<Integer, Team> teamMap = new HashMap<>();

    @Override
    public void save(Team team) {
        teamMap.put(team.getId(), team);
    }

    @Override
    public List<Team> findAll() {
        return new ArrayList<>(teamMap.values());
    }

    @Override
    public Optional<Team> findById(int teamId) {
        return Optional.ofNullable(teamMap.get(teamId));
    }
}
