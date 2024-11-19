package find_your_house.repository;

import find_your_house.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    @Query("select r from Reservation r where r.dateDebut between :startDate and :endDate")
    List<Reservation> findReservationByDate(@Param("startDate") Date startDate,@Param("endDate") Date endDate);
}
