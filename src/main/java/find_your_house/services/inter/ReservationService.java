package find_your_house.services.inter;

import find_your_house.entity.Reservation;
import find_your_house.handler.DateNotValidException;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    public Reservation postNewReservation(Reservation reservation) throws DateNotValidException;
    public Reservation updateReservation(Reservation reservation);
    public Reservation getReservationById(Integer id);
    public List<Reservation> getAllReservation();
    public void  deleteReservation(Integer id);
    public List<Reservation> getAllReservationsBetweenToDate(Date startDate , Date endDate);
}
