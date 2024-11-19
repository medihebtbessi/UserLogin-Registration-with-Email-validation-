package find_your_house.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.Date;

@Data
public class AbonnementRequest {
    @Max(12)
    @Min(1)
    private int nbMonth;
    private double prix;
    private boolean expired;
    private String typeAbonnement;
}
