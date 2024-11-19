package find_your_house.services.impl;

import find_your_house.dto.AbonnementRequest;
import find_your_house.entity.Abonnement;
import find_your_house.entity.Administrateur;
import find_your_house.entity.Agent;
import find_your_house.entity.Client;
import find_your_house.repository.AbonnementRepository;
import find_your_house.repository.AgentRepository;
import find_your_house.repository.ClientRepository;
import find_your_house.services.inter.AbonnementService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AbonnementServiceImpl implements AbonnementService {
    private final AbonnementRepository abonnementRepository;
    private final AgentRepository agentRepository;



    public Abonnement addNewAbonnement(Abonnement abonnementRequest){
        Administrateur administrateur=(Administrateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Abonnement abonnement=new Abonnement();
        abonnement.setTypeAbonnement( abonnementRequest.getTypeAbonnement().toUpperCase());
        abonnement.setPrixMoi(abonnementRequest.getPrixMoi());
        abonnement.setStartDate(java.sql.Date.valueOf(LocalDate.now()));
        abonnement.setEndDate(abonnementRequest.getEndDate());
        abonnement.setAdministrateur(administrateur);
        return abonnementRepository.save(abonnement);

    }
    public Abonnement updateAbonnement(Abonnement abonnement){
        Optional<Abonnement> abonnementOptional=abonnementRepository.findById(abonnement.getIdAbonnement());
        if (abonnementOptional.isPresent()){
            abonnementOptional.get().setTypeAbonnement(abonnement.getTypeAbonnement().toUpperCase());
//            abonnementOptional.get().setExpired(abonnement.isExpired());
//            abonnementOptional.get().setPrixMois(abonnement.getPrixMois());
            abonnementOptional.get().setStartDate(abonnement.getStartDate());
            abonnementOptional.get().setEndDate(abonnement.getEndDate());
            return abonnementRepository.save(abonnementOptional.get());
        }else {
            throw new EntityNotFoundException("Abonnement Not Found");
        }
    }

    public List<Abonnement> getAllAbonnement(){
        return abonnementRepository.findAll();
    }

    public Abonnement getAbonnementById(Integer id){
        Optional<Abonnement> abonnementOptional=abonnementRepository.findById(id);
        if (abonnementOptional.isPresent()){
            return abonnementOptional.get();
        }else {
            throw new EntityNotFoundException("Abonnement Not Found");
        }
    }

    public void deleteAbonnementById(Integer id){
        Optional<Abonnement> abonnementOptional=abonnementRepository.findById(id);
        if (abonnementOptional.isPresent()){
            abonnementRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Abonnement Not Found");
        }
    }

    public List<String> getAllTypeAbonnement(){
        return abonnementRepository.getAllNameAbonnement();
    }




}
