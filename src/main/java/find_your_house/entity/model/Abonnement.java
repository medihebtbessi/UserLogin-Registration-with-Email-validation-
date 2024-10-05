package find_your_house.entity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAbonnement;
    private Date duree;
    private double prix;
    @Enumerated(EnumType.STRING)
    private TypeAbonnement typeAbonnement;
}
