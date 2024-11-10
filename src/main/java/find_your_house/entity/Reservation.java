package find_your_house.entity;

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
}
