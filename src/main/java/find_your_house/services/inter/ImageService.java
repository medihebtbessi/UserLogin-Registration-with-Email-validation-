package find_your_house.services.inter;

import find_your_house.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    Image uplaodImageProd(MultipartFile file, Integer idoffre) throws IOException;
    List<Image> getImagesParProd(Integer offreId);
}
