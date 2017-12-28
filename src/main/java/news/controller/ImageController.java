package news.controller;

import news.repository.FileObjectRepository;
import news.service.ImageConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImageController {

    @Autowired
    private FileObjectRepository fileObjectRepository;
    @Autowired
    private ImageConfigService imageConfigService;

    @RequestMapping("/images/{id}")
    public ResponseEntity<byte[]> retrieveImage(Model model, @PathVariable Long id) {
        return imageConfigService.createResponse(fileObjectRepository.getOne(id));
    }
    
}
