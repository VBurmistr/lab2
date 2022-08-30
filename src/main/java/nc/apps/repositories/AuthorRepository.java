package nc.apps.repositories;

import nc.apps.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
