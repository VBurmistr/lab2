package nc.apps.repositories;

import nc.apps.entities.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookRepository extends JpaRepository<Book,Integer> , JpaSpecificationExecutor<Book> {
    Page<Book> findAll(Specification<Book> specification, Pageable pageable);
}
