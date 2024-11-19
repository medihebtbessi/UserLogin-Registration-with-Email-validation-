package find_your_house.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AbonnementVendu {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date startDate;
    private Date endDate;
    private int nbMoi;
    private double prixTotal;
    private boolean valualable;
    @OneToOne(mappedBy = "abonnementVendu")
    private Agent agent;
    @ManyToOne()
    private Abonnement abonnement;



}
