package find_your_house.config;

import find_your_house.entity.Reservation;
import org.springframework.batch.item.ItemProcessor;

public class ReservationProcessor implements ItemProcessor<Reservation,Reservation> {
    @Override
    public Reservation process(Reservation reservation) throws Exception {
//        reservation.setClient(null);
//        reservation.setAgent(null);
//        reservation.setAdministrateur(null);
        return reservation;
    }
}
