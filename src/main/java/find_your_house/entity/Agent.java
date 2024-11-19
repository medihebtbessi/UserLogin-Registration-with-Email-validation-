package find_your_house.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
public class Agent extends User {

    @Lob
    private byte[] battenteFiscale;
    @OneToMany(mappedBy = "agent")
    private List<Messagerie> messageries;
    @OneToMany(mappedBy = "agent")
    private List<Offre> offres;
    @OneToOne()
    private AbonnementVendu abonnementVendu;
    @ManyToMany(mappedBy = "agents")
    private List<Notification> notification;
    @OneToMany(mappedBy = "agent")
    private List<Reclamation> reclamations;
    @OneToMany(mappedBy = "agent")
    private List<Contrat> contrats;
    @OneToMany(mappedBy = "agent")
    private List<Reservation> reservations;

}
