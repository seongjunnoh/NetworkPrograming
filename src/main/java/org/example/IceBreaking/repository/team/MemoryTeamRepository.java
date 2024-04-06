package org.example.IceBreaking.repository.team;

import org.example.IceBreaking.domain.Team;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryTeamRepository implements TeamRepository{

    private final Map<String, Team> teamMap = new HashMap<>();

    @Override
    public void save(Team team) {
        teamMap.put(team.getTeamName(), team);
    }

    @Override
    public List<Team> findAll() {
        return new ArrayList<>(teamMap.values());
    }
}
