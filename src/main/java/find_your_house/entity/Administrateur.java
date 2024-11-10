package find_your_house.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
public class Administrateur extends User {

    @OneToMany(mappedBy = "administrateur")
    private List<Messagerie> messagerie;
    @OneToMany(mappedBy = "administrateur")
    private List<Reservation> reservation;
    @OneToMany(mappedBy = "administrateur")
    private List<Abonnement> abonnements;
    @ManyToMany(mappedBy = "administrateurs")
    private List<Notification> notification;
    @OneToMany(mappedBy = "administrateur")
    private List<Reclamation> reclamations;
}
