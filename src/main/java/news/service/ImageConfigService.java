package news.service;

import news.domain.FileObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ImageConfigService {

    public ResponseEntity<byte[]> createResponse(FileObject fo) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fo.getContentType()));
        headers.setContentLength(fo.getContentLength());
        return new ResponseEntity<>(fo.getContent(), headers, HttpStatus.CREATED);
    }

}
