package news.controller;

import org.springframework.web.bind.annotation.*;
import news.repository.FileObjectRepository;
import news.service.ImageConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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
