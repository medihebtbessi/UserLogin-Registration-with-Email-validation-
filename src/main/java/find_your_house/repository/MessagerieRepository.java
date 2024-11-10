package find_your_house.repository;

import find_your_house.entity.Messagerie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagerieRepository extends JpaRepository<Messagerie,Integer> {
}
