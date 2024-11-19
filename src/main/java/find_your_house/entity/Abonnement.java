package find_your_house.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Month;
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
    private Date startDate;
    private Date endDate;
    private double prixMoi;
    private String typeAbonnement;
    @OneToMany(mappedBy = "abonnement")
    private List<AbonnementVendu> abonnementVendu;
    @ManyToOne()
    private Administrateur administrateur;

}
