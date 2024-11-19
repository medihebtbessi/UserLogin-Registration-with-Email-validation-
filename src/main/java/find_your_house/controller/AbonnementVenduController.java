package find_your_house.controller;

import find_your_house.entity.AbonnementVendu;
import find_your_house.services.inter.AbonnementVenduService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("abonnementVendu")
@Tag(name = "Abonnement Vendu Controller")
public class AbonnementVenduController {

    private final AbonnementVenduService abonnementVenduService;

    @PostMapping("/affecter/{id}")
    public ResponseEntity<?> affecterAbonnement(@PathVariable("id") Integer idAbonnement, @RequestBody AbonnementVendu abonnementVendu){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.abonnementVenduService.affecterAbonnementAagent(idAbonnement, abonnementVendu));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
