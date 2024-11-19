package find_your_house.services.inter;

import find_your_house.dto.AbonnementRequest;
import find_your_house.entity.Abonnement;

import java.util.List;

public interface AbonnementService {

    public Abonnement addNewAbonnement(Abonnement abonnementRequest);
    public Abonnement updateAbonnement(Abonnement abonnement);
    public List<Abonnement> getAllAbonnement();
    public Abonnement getAbonnementById(Integer id);
    public void deleteAbonnementById(Integer id);
    public List<String> getAllTypeAbonnement();
}
