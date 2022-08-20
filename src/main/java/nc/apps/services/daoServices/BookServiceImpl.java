package nc.apps.services.daoServices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.BookDAO;
import nc.apps.dto.BookDBFilter;
import nc.apps.dto.BookTable;
import nc.apps.dto.SearchFiltersFromForm;
import nc.apps.entities.Book;
import nc.apps.mappers.SearchFilterToBookFilterMapping;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class BookServiceImpl implements BookService {
    public static final int TABLE_ROW_LIMIT = 10;
    BookDAO bookDAO;
    @Autowired
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public Book getBookById(int id) throws ServiceException {
        try {
            return bookDAO.getById(id);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    public boolean updateBook(Book book,int id) throws ServiceException {
        try {
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
    public boolean addBook(Book book) throws ServiceException {
        try {
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
            int size = bookDAO.allBooksSize(filter);
            long totalPages =  (long) Math.ceil(size/(float)TABLE_ROW_LIMIT);
            return new BookTable(bookList,totalPages, searchFiltersFromForm.getPage());
        }catch (DAOException e){
            throw new ServiceException(e);
        }

    }
}
