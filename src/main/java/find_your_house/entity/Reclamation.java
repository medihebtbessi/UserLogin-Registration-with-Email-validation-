package find_your_house.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReclamation;
    private Instant dateReclamation=Instant.now();
    private String description;
    @ManyToOne()
    private Client client;
    @ManyToOne
    private Agent agent;
    @ManyToOne
    private Administrateur administrateur;
}
