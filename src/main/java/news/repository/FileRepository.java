package news.repository;

import news.domain.FileObject;
import news.domain.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileObject, Long> {
    
}