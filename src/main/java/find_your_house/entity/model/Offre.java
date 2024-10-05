package find_your_house.entity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import javax.xml.crypto.Data;
@Entity
@Getter
@Setter
public class Offre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOffre;
    private String nom;
    private double prix;
    private String address;
    private String description;
    private Data periodeDisponibilite;
}
