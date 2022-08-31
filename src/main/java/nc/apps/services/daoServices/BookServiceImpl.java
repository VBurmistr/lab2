package nc.apps.services.daoServices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.BookDAO;
import nc.apps.dto.BookDBFilter;
import nc.apps.dto.BookIDsDTO;
import nc.apps.dto.tabledtos.BookDTO;
import nc.apps.dto.tabledtos.BookTable;
import nc.apps.dto.SearchFiltersFromForm;
import nc.apps.entities.domain.Book;
import nc.apps.mappers.DTOToDomainMapper;
import nc.apps.mappers.DomainToDTOMapper;
import nc.apps.mappers.SearchFilterToBookFilterMapping;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class BookServiceImpl implements BookService {
    public static final int TABLE_ROW_LIMIT = 10;
    private final BookDAO bookDAO;
    @Autowired
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public BookDTO getBookById(int id) throws ServiceException {
        try {
            return DomainToDTOMapper.mapBook(bookDAO.getById(id));
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    public boolean updateBook(BookIDsDTO bookIDsDTO, int id) throws ServiceException {
        try {
            Book book = DTOToDomainMapper.mapBook(bookIDsDTO);
            book.setId(id);
            bookDAO.update(book);
            return true;
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    public boolean removeBook(int id) throws ServiceException {
        try {
            bookDAO.remove(id);
            return true;
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }
    public boolean addBook(BookIDsDTO bookIDsDTO) throws ServiceException {
        try {
            Book book = DTOToDomainMapper.mapBook(bookIDsDTO);
            bookDAO.save(book);
            return true;
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    public BookTable getAllBooksOnPage(SearchFiltersFromForm searchFiltersFromForm) throws ServiceException {
        try {

            BookDBFilter filter = SearchFilterToBookFilterMapping.map(searchFiltersFromForm,TABLE_ROW_LIMIT);
            List<Book> bookList = bookDAO.getAll(filter);
            List<BookDTO> bookListDto = DomainToDTOMapper.mapBooks(bookList);
            int size = bookDAO.allBooksSize(filter);
            long totalPages =  (long) Math.ceil(size/(float)TABLE_ROW_LIMIT);
            return new BookTable(bookListDto,totalPages, searchFiltersFromForm.getPage());
        }catch (DAOException e){
            throw new ServiceException(e);
        }

    }
}
