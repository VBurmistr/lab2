package nc.apps.services.jpaservices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dto.BookDBFilter;
import nc.apps.dto.BookTable;
import nc.apps.dto.SearchFiltersFromForm;
import nc.apps.entities.Book;
import nc.apps.mappers.BookPersistedToDetached;
import nc.apps.mappers.SearchFilterToBookFilterMapping;
import nc.apps.repositories.BookRepository;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.BookService;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Primary
public class BookJPAServiceImpl implements BookService {
    public static final int TABLE_ROW_LIMIT = 10;
    private final BookRepository bookRepository;

    public BookJPAServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBookById(int id) throws ServiceException {
        try {
            Optional<Book> book = bookRepository.findById(id);
            if (book.isPresent()) {
                return book.get();
            }
            throw new ServiceException("No books with that id");
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public boolean updateBook(Book book, int id) throws ServiceException {
        try {
            book.setId(id);
            bookRepository.save(book);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    public boolean removeBook(int id) throws ServiceException {
        try {
            bookRepository.findById(id)
                    .ifPresent(b -> {
                        b.getChilds()
                                .forEach(c -> {
                                    c.setPrequel(null);
                                });
                    });
            bookRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public boolean addBook(Book book) throws ServiceException {
        try {
            bookRepository.save(book);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public BookTable getAllBooksOnPage(SearchFiltersFromForm searchFiltersFromForm) throws ServiceException {
        try {
            BookDBFilter filter = SearchFilterToBookFilterMapping.map(searchFiltersFromForm, TABLE_ROW_LIMIT);
            List<Book> bookListPersisted = bookRepository.findAllBy(PageRequest.of(searchFiltersFromForm.getPage() - 1, 10, Sort.by("title")));
            List<Book> books = BookPersistedToDetached.map(bookListPersisted);
            Long size = bookRepository.count();
            long totalPages = (long) Math.ceil(size / (float) TABLE_ROW_LIMIT);
            return new BookTable(books, totalPages, searchFiltersFromForm.getPage());
        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }
}
