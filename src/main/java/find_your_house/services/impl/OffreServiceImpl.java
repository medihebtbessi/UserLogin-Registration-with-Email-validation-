package find_your_house.services.impl;

import find_your_house.dto.ReservationDto;
import find_your_house.entity.Client;
import find_your_house.entity.Offre;
import find_your_house.entity.Reservation;
import find_your_house.notification.NotificationService;
import find_your_house.repository.OffreRepository;
import find_your_house.services.inter.OffreService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class OffreServiceImpl implements OffreService {

    private final OffreRepository offreRepository;

    public Offre postNewOffre(Offre offre){

        return offreRepository.save(offre);
    }

    public Offre updateOffre(Integer id,Offre offre){
        Optional<Offre> updatedOffre=offreRepository.findById(id);
        if (updatedOffre.isPresent()){

            updatedOffre.get().setNom(offre.getNom());
            updatedOffre.get().setDescription(offre.getDescription());
            updatedOffre.get().setAddress(offre.getAddress());
            updatedOffre.get().setPrix(offre.getPrix());
            //updatedOffre.setBattenteFiscale(offre.getBattenteFiscale());
            updatedOffre.get().setPeriodeDisponibilite(offre.getPeriodeDisponibilite());
            return offreRepository.save(updatedOffre.get());
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

    public List<ReservationDto> getAllReservationApartientOffre(Integer id){
        Optional<Offre> offre=offreRepository.findById(id);
        if (offre.isPresent()){
            List<ReservationDto> reservations=offre.get().getReservations().stream().map(Reservation::reservationToReservationDto).toList();
            return  reservations;
        }
        else {
            throw new EntityNotFoundException("Offre not Found");
        }

    }

    public List<Client> getAllClientMakeRes(Integer id){
        Optional<Offre> offre=offreRepository.findById(id);
        if (offre.isPresent()){
            List<Client> clients=offre.get().getReservations().stream().map(Reservation::getClient).toList();
            return  clients;
        }
        else {
            throw new EntityNotFoundException("Offre not Found");
        }
    }
}
