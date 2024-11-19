package find_your_house.controller;

import find_your_house.entity.Reservation;
import find_your_house.services.inter.ReservationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@RestController
@RequestMapping("reservation")
@Tag(name = "Reservation Controller")
public class ReservationController {

    private final ReservationService reservationService;
    private final JobLauncher jobLauncher;

    private final Job jobs;

    public ReservationController(ReservationService reservationService,JobLauncher jobLauncher,@Qualifier("runJobRev") Job jobs){
        this.reservationService=reservationService;
        this.jobLauncher=jobLauncher;
        this.jobs=jobs;
    }

    @PostMapping("/addReservation")
    public ResponseEntity<?> postNewReservation(@RequestBody Reservation reservation) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.postNewReservation(reservation));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PutMapping("/updateReservation")
    public ResponseEntity<?> updateReservation(@RequestBody Reservation reservation) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reservationService.updateReservation(reservation));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/getResById/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reservationService.getReservationById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllReservation() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reservationService.getAllReservation());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteRevById/{id}")
    public ResponseEntity<?> deleteOffre(@PathVariable("id") Integer id) {
        try {
            reservationService.deleteReservation(id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OK");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("getRevBetweenTwoDate")
    public ResponseEntity<?>  getAllReservationsBetweenToDate(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reservationService.getAllReservationsBetweenToDate(startDate, endDate));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("importReservation")
    public void importCsvToDBJob(){
        JobParameters jobParameters=new JobParametersBuilder()
                .addLong("startAt",System.currentTimeMillis())
                .toJobParameters();
        try {
            jobLauncher.run(jobs,jobParameters);
        }catch (JobExecutionAlreadyRunningException | JobParametersInvalidException |
                JobInstanceAlreadyCompleteException | JobRestartException e) {
            e.printStackTrace();
        }
    }


}
