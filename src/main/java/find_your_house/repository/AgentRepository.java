package find_your_house.repository;

import find_your_house.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent,Integer> {
}
