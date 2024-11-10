package find_your_house.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Offre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOffre;
    private String nom;
    private double prix;
    private String address;
    private String description;
    private Date periodeDisponibilite;
    @Lob
    private List<byte[]> battenteFiscale;
    @ManyToOne
    private Agent agent;
    @ManyToOne
    private Client client;

}
