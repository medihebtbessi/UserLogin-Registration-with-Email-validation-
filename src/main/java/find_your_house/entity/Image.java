package find_your_house.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImage;
    private String name;
    private String type;
    @Column(name = "IMAGE",length = 404876)
    @Lob
    private byte[] image;
    @ManyToOne()
    @JoinColumn(name = "Offre_ID")
    @JsonIgnore
    private Offre offre;
}
