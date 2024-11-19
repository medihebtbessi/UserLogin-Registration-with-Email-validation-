package find_your_house.services.impl;

import find_your_house.entity.Image;
import find_your_house.entity.Offre;
import find_your_house.repository.ImageRepository;
import find_your_house.repository.OffreRepository;
import find_your_house.services.inter.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final OffreRepository offreRepository;
    @Override
    public Image uplaodImageProd(MultipartFile file, Integer idOffre) throws IOException {

        Offre offre=new Offre();
        offre.setIdOffre(idOffre);
        return imageRepository.save(Image.builder()
                        .name(file.getName())
                        .type(file.getContentType())
                        .image(file.getBytes())
                        .offre(offre)
                .build());
    }

    @Override
    public List<Image> getImagesParProd(Integer idOffre) {
        Offre offre=offreRepository.findById(idOffre).get();
        return offre.getImage();
    }
}
