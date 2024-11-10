package find_your_house.services.inter;

import find_your_house.entity.Offre;

import java.util.List;

public interface OffreService {
    public Offre postNewOffre(Offre offre);
    public Offre updateOffre(Offre offre);
    public List<Offre> getAllOffre();
    public Offre getOffreById(Integer id);
    public void deleteOffre(Integer id);
}
