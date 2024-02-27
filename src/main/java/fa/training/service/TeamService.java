package fa.training.service;

import fa.training.model.Team;
import fa.training.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    public TeamRepository teamRepository;

    public void addTeam(Team team) {
        teamRepository.save(team);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(Integer id) {
        Optional<Team> team = teamRepository.findById(id);
        return team.isPresent() ? team.get() : null;
    }
    public void deleteTeam(Integer id) {
        teamRepository.deleteById(id);
    }

    public long getSizeTeam() {
        return getAllTeams().size();
    }
}
