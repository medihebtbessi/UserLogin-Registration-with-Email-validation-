package find_your_house.entity;

import find_your_house.dto.ReservationDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReservationn;
    private Date dateDebut;
    private Date dateFin;
    @Enumerated(EnumType.STRING)
    private ModePaiement modePaiement;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Agent agent;
    @ManyToOne
    private Administrateur administrateur;
    public static ReservationDto reservationToReservationDto(Reservation reservation){
        ReservationDto reservationDto=new ReservationDto(reservation.getIdReservationn(),reservation.getDateDebut(),reservation.getDateFin(),reservation.getModePaiement());
        return reservationDto;
    }
}
