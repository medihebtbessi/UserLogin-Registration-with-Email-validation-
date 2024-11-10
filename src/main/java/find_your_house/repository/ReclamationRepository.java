package find_your_house.repository;

import find_your_house.entity.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamationRepository extends JpaRepository<Reclamation,Integer> {
}
