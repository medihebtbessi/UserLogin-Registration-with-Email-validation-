package find_your_house.repository;

import find_your_house.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent,Integer> {
    Optional<Agent> findByEmail(String username);
}
