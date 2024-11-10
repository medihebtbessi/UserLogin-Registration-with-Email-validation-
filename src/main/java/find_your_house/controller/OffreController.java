package find_your_house.controller;

import find_your_house.entity.Offre;
import find_your_house.services.inter.OffreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "Offre Controller")
public class OffreController {
    @Autowired
    private final OffreService offreService;

    @PostMapping("/addOffre")
    @Operation(description = "post new offre")
    public ResponseEntity<?> postNewOffre(@RequestParam Offre offre) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(offreService.postNewOffre(offre));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/updateOffre")
    @Operation(description = "update offre")
    public ResponseEntity<?> updateOffre(@RequestParam Offre offre) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(offreService.updateOffre(offre));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/getAllOffre")
    public ResponseEntity<?> getAllOffre() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(offreService.getAllOffre());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/getOffre/{id}")
    public ResponseEntity<?> getOffreById(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(offreService.getOffreById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<? > deleteOffre(@PathVariable("id") Integer id) {
        try {
            offreService.deleteOffre(id);
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
