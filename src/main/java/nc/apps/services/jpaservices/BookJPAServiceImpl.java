package nc.apps.services.jpaservices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dto.BookIDsDTO;
import nc.apps.dto.tabledtos.BookTable;
import nc.apps.dto.SearchFiltersFromForm;
import nc.apps.dto.tabledtos.BookDTO;
import nc.apps.entities.domain.Book;
import nc.apps.mappers.*;
import nc.apps.repositories.BookRepository;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.BookService;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Primary
public class BookJPAServiceImpl implements BookService {
    private static final int TABLE_ROW_LIMIT = 10;
    private final BookRepository bookRepository;

    public BookJPAServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public BookDTO getBookById(int id) throws ServiceException {
        try {
            Optional<Book> book = bookRepository.findById(id);
            if (book.isPresent()) {
                return DomainToDTOMapper.mapBook(book.get());
            }
            throw new ServiceException("No books with that id");
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateBook(BookIDsDTO bookIDsDTO, int id) throws ServiceException {
        try {
            Book book = DTOToDomainMapper.mapBook(bookIDsDTO);
            book.setId(id);
            bookRepository.save(book);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    @Override
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
    @Override
    public boolean addBook(BookIDsDTO bookIDsDTO) throws ServiceException {
        try {
            Book book = DTOToDomainMapper.mapBook(bookIDsDTO);
            bookRepository.save(book);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public BookTable getAllBooksOnPage(SearchFiltersFromForm searchFiltersFromForm) throws ServiceException {
        try {

            Specification<Book> specs = SpringDataSpecificationMapper.mapSpecs(searchFiltersFromForm);
            Sort sort = SpringDataSortMapping.map(searchFiltersFromForm);
            Page<Book> books = bookRepository.findAll(specs,
                    PageRequest.of(searchFiltersFromForm.getPage() - 1, TABLE_ROW_LIMIT, sort));

            List<BookDTO> bookDTOs = DomainToDTOMapper.mapBookPage(books);
            return new BookTable(bookDTOs, books.getTotalPages(), searchFiltersFromForm.getPage());
        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }
}
