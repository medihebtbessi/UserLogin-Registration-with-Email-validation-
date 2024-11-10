package find_your_house.repository;

import find_your_house.entity.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratRepository extends JpaRepository<Contrat,Integer> {
}
