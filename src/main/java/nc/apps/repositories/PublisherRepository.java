package nc.apps.repositories;

import nc.apps.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher,Integer> {
}
