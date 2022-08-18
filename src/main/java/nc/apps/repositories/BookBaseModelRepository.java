package nc.apps.repositories;

import nc.apps.entities.Book;
import nc.apps.entities.BookBaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookBaseModelRepository extends JpaRepository<BookBaseModel,Integer> {

}
