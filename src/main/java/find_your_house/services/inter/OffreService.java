package find_your_house.services.inter;

import find_your_house.dto.ReservationDto;
import find_your_house.entity.Client;
import find_your_house.entity.Offre;

import java.util.List;

public interface OffreService {
    public Offre postNewOffre(Offre offre);
    public Offre updateOffre(Integer id,Offre offre);
    public List<Offre> getAllOffre();
    public Offre getOffreById(Integer id);
    public void deleteOffre(Integer id);

    public List<ReservationDto> getAllReservationApartientOffre(Integer id);
    public List<Client> getAllClientMakeRes(Integer id);
}
