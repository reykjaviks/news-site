package news.service;

import java.io.IOException;
import news.domain.FileObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileObjectConfigService {

    public FileObject createFileObject(MultipartFile file) throws IOException {
        FileObject fo = new FileObject();
        fo.setContent(file.getBytes());
        fo.setContentLength(file.getSize());
        fo.setContentType(file.getContentType());
        return fo;
    }

}
