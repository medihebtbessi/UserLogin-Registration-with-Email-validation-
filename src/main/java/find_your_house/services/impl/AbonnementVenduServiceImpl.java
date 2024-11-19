package find_your_house.services.impl;

import find_your_house.entity.Abonnement;
import find_your_house.entity.AbonnementVendu;
import find_your_house.entity.Agent;
import find_your_house.repository.AbonnementRepository;
import find_your_house.repository.AbonnementVenduRepository;
import find_your_house.repository.AgentRepository;
import find_your_house.services.inter.AbonnementVenduService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Builder
public class AbonnementVenduServiceImpl implements AbonnementVenduService {
    private final AbonnementVenduRepository abonnementVenduRepository;
    private final AbonnementRepository abonnementRepository;
    private final AgentRepository agentRepository;

    public AbonnementVendu affecterAbonnementAagent(Integer abonnementId,AbonnementVendu abonnementVendu){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(username);
        Agent client =  agentRepository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé"));
        Optional<Abonnement> abonnementOptional=abonnementRepository.findById(abonnementId);
        Optional<Agent> agentOptional =agentRepository.findById(client.getId());
        if (abonnementOptional.isPresent()&&agentOptional.isPresent()){
            return abonnementVenduRepository.save(AbonnementVendu.builder()
                            .abonnement(abonnementOptional.get())
                            .agent(agentOptional.get())
                            .nbMoi(abonnementVendu.getNbMoi())
                            .startDate(java.sql.Date.valueOf(LocalDate.now()))
                            .endDate(java.sql.Date.valueOf(LocalDate.now().plusMonths(abonnementVendu.getNbMoi())))
                            .prixTotal(abonnementOptional.get().getPrixMoi()*abonnementVendu.getNbMoi())
                            .valualable(false)
                    .build());
        }else {
            throw new EntityNotFoundException("L'un des entites n'existe pas");
        }
    }
}
