package find_your_house.entity.model;

import find_your_house.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Agent extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAgent;
    @Lob
    private byte[] battenteFiscale;
    @Enumerated(EnumType.STRING)
    private Role role;
}
