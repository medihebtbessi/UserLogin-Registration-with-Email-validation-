package find_your_house.entity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
@Entity
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReservationn;
    private Date dateDebut;
    private Date dateFin;
    @Enumerated(EnumType.STRING)
    private ModePaiement modePaiement;
}
