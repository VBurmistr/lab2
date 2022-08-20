package nc.apps.services.jpaservices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dto.BookDBFilter;
import nc.apps.dto.BookIDsDTO;
import nc.apps.dto.tabledtos.BookTable;
import nc.apps.dto.SearchFiltersFromForm;
import nc.apps.dto.tabledtos.BookDTO;
import nc.apps.entities.Book;
import nc.apps.mappers.DTOToDomainMapper;
import nc.apps.mappers.DomainToDTOMapper;
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

@Slf4j
@Service
public class BookJPAServiceImpl implements BookService {
    public static final int TABLE_ROW_LIMIT = 10;
    private final BookRepository bookRepository;

    public BookJPAServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

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

    public boolean addBook(BookIDsDTO bookIDsDTO) throws ServiceException {
        try {
            Book book = DTOToDomainMapper.mapBook(bookIDsDTO);
            bookRepository.save(book);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public BookTable getAllBooksOnPage(SearchFiltersFromForm searchFiltersFromForm) throws ServiceException {
        try {
            BookDBFilter filter = SearchFilterToBookFilterMapping.map(searchFiltersFromForm, TABLE_ROW_LIMIT);
            List<Book> books = bookRepository.findAllBy(PageRequest.of(searchFiltersFromForm.getPage() - 1, 10, Sort.by("title")));
            List<BookDTO> bookDTOs = DomainToDTOMapper.mapBooks(books);
            long size = bookRepository.count();
            long totalPages = (long) Math.ceil(size / (float) TABLE_ROW_LIMIT);
            return new BookTable(bookDTOs, totalPages, searchFiltersFromForm.getPage());
        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }
}
