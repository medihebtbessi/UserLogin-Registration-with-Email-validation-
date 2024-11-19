package find_your_house.services.impl;


import find_your_house.entity.Reservation;
import find_your_house.handler.DateNotValidException;
import find_your_house.notification.Notification;
import find_your_house.notification.NotificationService;
import find_your_house.notification.NotificationStatus;
import find_your_house.repository.ReservationRepository;
import find_your_house.services.inter.ReservationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final NotificationService notificationService;
    public Reservation postNewReservation(Reservation reservation) throws DateNotValidException {
        if (reservation.getDateDebut().after(reservation.getDateFin())){
            throw new DateNotValidException("Date debut doit etre superieur");
        }else
        {
            notificationService.sendNotification(reservation.getAgent().getName(),
                    Notification.builder()
                            .status(NotificationStatus.RESERVATION_AFFECTED)
                            .massage("Reservation has been affected")
                            .reservationName("Res")
                            .build());
            return reservationRepository.save(reservation);
        }

    }

    public Reservation updateReservation(Reservation reservation){
        Optional<Reservation> updatedResv=reservationRepository.findById(reservation.getIdReservationn());
        if (updatedResv.isPresent()){
            updatedResv.get().setDateDebut(reservation.getDateDebut());
            updatedResv.get().setDateFin(reservation.getDateFin());
            updatedResv.get().setModePaiement(reservation.getModePaiement());
            return reservationRepository.save(updatedResv.get());
        }else
            throw new EntityNotFoundException("cette reservation n'existe pas ");

    }

    public Reservation getReservationById(Integer id){
        Optional<Reservation> reservation=reservationRepository.findById(id);
        if (reservation.isPresent()){
            return reservation.get();
        }
        else {
            throw new EntityNotFoundException("cette reservation n'existe pas ");
        }
    }

    public List<Reservation> getAllReservation(){
        return reservationRepository.findAll();
    }


    public void deleteReservation(Integer id){
        Optional<Reservation> reservation=reservationRepository.findById(id);
        if (reservation.isPresent()){
            reservationRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("cette reservation n'existe pas ");
        }
    }

    public List<Reservation> getAllReservationsBetweenToDate(Date startDate , Date endDate){
        return reservationRepository.findReservationByDate(startDate,endDate);
    }


}
