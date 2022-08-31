package nc.apps.repositories;

import nc.apps.entities.domain.BookBaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookBaseModelRepository extends JpaRepository<BookBaseModel,Integer> {
}
