package find_your_house.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAbonnement;
    private Date duree;
    private double prix;
    @Enumerated(EnumType.STRING)
    private TypeAbonnement typeAbonnement;
    @OneToMany(mappedBy = "abonnement")
    private List<Agent> agent;
    @ManyToOne()
    private Administrateur administrateur;
}
