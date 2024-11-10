package find_your_house.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data

public class Client extends User {

    @OneToMany(mappedBy = "client")
    private List<Contrat> contrats;
    @OneToMany(mappedBy = "client")
    private List<Messagerie> messagerie;
    @OneToMany(mappedBy = "client")
    private List<Reclamation> reclamations;
    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations;
    @ManyToMany(mappedBy = "client")
    private List<Notification> notification;
    @OneToMany(mappedBy = "client")
    private List<Offre> offre;

}
