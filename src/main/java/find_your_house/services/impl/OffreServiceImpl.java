package find_your_house.services.impl;

import find_your_house.entity.Offre;
import find_your_house.repository.OffreRepository;
import find_your_house.services.inter.OffreService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OffreServiceImpl implements OffreService {
    private final OffreRepository offreRepository;

    public Offre postNewOffre(Offre offre){
        return offreRepository.save(offre);
    }

    public Offre updateOffre(Offre offre){
        Optional<Offre> existOffre=offreRepository.findById(offre.getIdOffre());
        if (existOffre.isPresent()){
            Offre updatedOffre=new Offre();
            updatedOffre.setNom(offre.getNom());
            updatedOffre.setDescription(offre.getDescription());
            updatedOffre.setAddress(offre.getAddress());
            updatedOffre.setPrix(offre.getPrix());
            updatedOffre.setBattenteFiscale(offre.getBattenteFiscale());
            updatedOffre.setPeriodeDisponibilite(offre.getPeriodeDisponibilite());
            return offreRepository.save(updatedOffre);
        }else {
            throw new EntityNotFoundException("Cette offre n'exist pas");
        }
    }

    public List<Offre> getAllOffre(){
        return offreRepository.findAll();
    }

    public Offre getOffreById(Integer id){
        Optional<Offre> offre=offreRepository.findById(id);
        if (offre.isPresent()){
            return offre.get();
        }else
            throw new RuntimeException("Offre not found ");
    }

    public void deleteOffre(Integer id){
        Optional<Offre> offre=offreRepository.findById(id);
        if (offre.isPresent()){
             offreRepository.deleteById(id);
        }else
            throw new RuntimeException("Offre not found ");
    }
}
