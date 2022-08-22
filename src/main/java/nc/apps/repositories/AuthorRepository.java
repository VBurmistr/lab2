package nc.apps.repositories;

import nc.apps.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Optional<Author> findByFirstNameAndLastName(String firstName,String lastName);
}
