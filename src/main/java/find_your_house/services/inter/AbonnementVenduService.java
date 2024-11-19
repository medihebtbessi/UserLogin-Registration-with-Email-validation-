package find_your_house.services.inter;

import find_your_house.entity.AbonnementVendu;

public interface AbonnementVenduService {
    public AbonnementVendu affecterAbonnementAagent(Integer abonnementId, AbonnementVendu abonnementVendu);
}
