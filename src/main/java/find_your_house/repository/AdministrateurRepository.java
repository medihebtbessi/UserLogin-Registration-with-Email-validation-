package find_your_house.repository;

import find_your_house.entity.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrateurRepository extends JpaRepository<Administrateur,Integer> {
}
