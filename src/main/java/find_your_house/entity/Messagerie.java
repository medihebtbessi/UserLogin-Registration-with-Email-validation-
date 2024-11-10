package find_your_house.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Messagerie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMessage;
    private String message;
    @ManyToOne()
    private Administrateur administrateur;
    @ManyToOne
    private Agent agent;
    @ManyToOne
    private Client client;
}
