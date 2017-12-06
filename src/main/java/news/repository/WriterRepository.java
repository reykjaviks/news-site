package news.repository;

import news.domain.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriterRepository extends JpaRepository<Writer, Long> {
    
    Writer findByName(String name);
    
}
