package find_your_house.controller;

import find_your_house.entity.Image;
import find_your_house.services.inter.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("image")
public class ImageController {

    private final ImageService imageService;
    @PostMapping(value = "/uploadImageOffre/{idOffre}")
    public Image uploadMultiImages(@RequestParam("image") MultipartFile file,
                                   @PathVariable("idOffre") Integer idOffre)
            throws IOException {
        return imageService.uplaodImageProd(file,idOffre);
    }

    @RequestMapping(value = "/getImagesProd/{idOffre}" ,
            method = RequestMethod.GET)
    public List<Image> getImagesOffre(@PathVariable("idOffre") Integer idOffre)
            throws IOException {
        return imageService.getImagesParProd(idOffre);
    }


}
