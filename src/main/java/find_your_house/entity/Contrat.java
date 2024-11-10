package find_your_house.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContrat;
    private String loi;
    @ManyToOne()
    private Contrat contrat;
    @ManyToOne()
    private Agent agent;
    @ManyToOne()
    private Client client;

}
