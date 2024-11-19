package find_your_house.controller;

import find_your_house.entity.Offre;
import find_your_house.services.inter.OffreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("offre")
@Tag(name = "Offre Controller")
public class OffreController {
    @Autowired
    private final OffreService offreService;
    private final JobLauncher jobLauncher;

    private final Job job;

    public OffreController(OffreService offreService,JobLauncher jobLauncher,@Qualifier("runJob") Job job){
        this.offreService=offreService;
        this.jobLauncher=jobLauncher;
        this.job=job;
    }


    @PostMapping("/addOffre")
    @Operation(description = "post new offre")
    public ResponseEntity<?> postNewOffre(@RequestBody Offre offre) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(offreService.postNewOffre(offre));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/updateOffre/{id}")
    @Operation(description = "update offre")
    public ResponseEntity<?> updateOffre(@PathVariable("id") Integer id,@RequestBody Offre offre) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(offreService.updateOffre(id,offre));
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
    @GetMapping("getResByOffre/{id}")
    public ResponseEntity<?> getResByOffre(@PathVariable("id") Integer id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(offreService.getAllReservationApartientOffre(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("importOffre")
    public void importCsvToDBJob(){
        JobParameters jobParameters=new JobParametersBuilder()
                .addLong("startAt",System.currentTimeMillis())
                .toJobParameters();
        try {
            jobLauncher.run(job,jobParameters);
        }catch (JobExecutionAlreadyRunningException | JobParametersInvalidException |
                JobInstanceAlreadyCompleteException | JobRestartException e) {
            e.printStackTrace();
        }
    }
}
