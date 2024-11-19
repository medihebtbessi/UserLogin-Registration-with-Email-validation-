package find_your_house.config;

import find_your_house.entity.Offre;
import org.springframework.batch.item.ItemProcessor;

public class OffreProcessor implements ItemProcessor<Offre,Offre> {

    @Override
    public Offre process(Offre offre) throws Exception {
//     offre.setBattenteFiscale(null);
//        offre.setAgent(null);
//        offre.setClient(null);
        return offre;
    }
}
