package find_your_house.repository;

import find_your_house.entity.Abonnement;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbonnementRepository extends JpaRepository<Abonnement,Integer> {

    @Query("select a.typeAbonnement from Abonnement a")
    public List<String> getAllNameAbonnement();
}
