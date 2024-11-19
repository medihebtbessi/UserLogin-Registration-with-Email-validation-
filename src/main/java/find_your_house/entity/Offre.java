package find_your_house.entity;

import find_your_house.dto.ReservationDto;
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
    @OneToMany(mappedBy = "offre")
    private List<Image> image;
    @ManyToOne
    private Agent agent;
    @ManyToOne
    private Client client;
    @OneToMany()
    private List<Reservation> reservations;



}
