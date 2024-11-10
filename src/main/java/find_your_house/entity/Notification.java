package find_your_house.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNotification;
    private Instant date=Instant.now();
    private String titre;
    private String details;
    @ManyToMany()
    private List<Agent> agents;
    @ManyToMany()
    private List<Client> client;
    @ManyToMany()
    private List<Administrateur> administrateurs;
}
