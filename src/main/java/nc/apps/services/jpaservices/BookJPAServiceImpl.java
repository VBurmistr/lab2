package nc.apps.services.jpaservices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.BookDAO;
import nc.apps.dto.BookDBFilter;
import nc.apps.dto.BookTable;
import nc.apps.dto.SearchFiltersFromForm;
import nc.apps.entities.Book;
import nc.apps.entities.BookBaseModel;
import nc.apps.mappers.SearchFilterToBookFilterMapping;
import nc.apps.repositories.BookRepository;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookJPAServiceImpl implements BookService {
    public static final int TABLE_ROW_LIMIT = 10;
    BookRepository bookRepository;

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
            if (book.getPrequel() == null || book.getPrequel().getId() == null) {
                book.setPrequel(null);
                bookRepository.save(book);
            } else {
                bookRepository.save(book);
            }
            return true;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public boolean removeBook(int id) throws ServiceException {
        try {
            bookRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public boolean addBook(Book book) throws ServiceException {
        try {
            if (book.getPrequel() == null || book.getPrequel().getId() == null) {
                book.setPrequel(null);
                bookRepository.save(book);
            } else {
                bookRepository.save(book);
            }
            return true;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public BookTable getAllBooksOnPage(SearchFiltersFromForm searchFiltersFromForm) throws ServiceException {
        try {
            BookDBFilter filter = SearchFilterToBookFilterMapping.map(searchFiltersFromForm, TABLE_ROW_LIMIT);
            List<Book> bookList = bookRepository.findAllBy(PageRequest.of(searchFiltersFromForm.getPage()-1,10,Sort.by("id")));
            Long size = bookRepository.count();
            long totalPages = (long) Math.ceil(size / (float) TABLE_ROW_LIMIT);
            return new BookTable(bookList, totalPages, searchFiltersFromForm.getPage());
        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }
}
