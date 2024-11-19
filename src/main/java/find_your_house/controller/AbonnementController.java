package find_your_house.controller;

import find_your_house.dto.AbonnementRequest;
import find_your_house.entity.Abonnement;
import find_your_house.services.inter.AbonnementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("abonnement")
@Tag(name = "Abonnement Controller")
public class AbonnementController {

    private final AbonnementService abonnementService;

    @PostMapping("addAbonnement")
    public ResponseEntity<?> addNewAbonnement(@RequestBody Abonnement abonnementRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(abonnementService.addNewAbonnement(abonnementRequest));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("updateAbonnement")
    public ResponseEntity<?> updateAbonnement(@RequestBody Abonnement abonnement) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(abonnementService.updateAbonnement(abonnement));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("getAllAbonnement")
    public ResponseEntity<?> getAllAbonnement() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(abonnementService.getAllAbonnement());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("getAbonnementById/{id}")
    public ResponseEntity<?> getAbonnementById(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(abonnementService.getAbonnementById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @DeleteMapping("deleteAbonnement/{id}")
    public ResponseEntity<?> deleteAbonnementById(@PathVariable("id") Integer id) {
        try {
            abonnementService.deleteAbonnementById(id);
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("getAllTypeAbonnement")
    public ResponseEntity<?> getAllTypeAbonnement(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(abonnementService.getAllTypeAbonnement());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
