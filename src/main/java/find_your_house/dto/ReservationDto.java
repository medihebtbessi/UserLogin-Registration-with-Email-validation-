package find_your_house.dto;

import find_your_house.entity.ModePaiement;

import java.util.Date;

public record ReservationDto(Integer idReservationn, Date dateDebut, Date dateFin, ModePaiement modePaiement) {
}
