package nc.apps.repositories;

import nc.apps.entities.Book;
import nc.apps.entities.BookBaseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.PreRemove;
import javax.transaction.Transactional;
import java.util.List;
public interface BookRepository extends JpaRepository<Book,Integer> , JpaSpecificationExecutor<Book> {

    @Query(value = "select id, title from lab3_book_table",nativeQuery = true)
    List<BookBaseModel> findAllBookBaseModels();

    Page<Book> findAll(Specification<Book> specification, Pageable pageable);
    @Query(value = "INSERT INTO lab3_book_table(title, author_id, category_id, language_id, prequel_id, publisher_id) " +
            "VALUES (:title,:author_id,:category_id,:language_id,:prequel_id,:publisher_id)",
            nativeQuery = true)
    void addNewBook(String title,Integer author_id,Integer category_id, Integer language_id,Integer prequel_id,Integer publisher_id);

}
