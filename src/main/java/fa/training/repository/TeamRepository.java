package fa.training.repository;

import fa.training.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    @Override
    List<Team> findAll();

    @Override
    Optional<Team> findById(Integer integer);

    @Override
    long count();
}
