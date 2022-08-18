package nc.apps.services.interfaces;

import nc.apps.dto.BookTable;
import nc.apps.dto.SearchFiltersFromForm;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.entities.Book;

public interface BookService {
    Book getBookById(int id) throws ServiceException;
    boolean updateBook(Book book,int id) throws ServiceException;
    boolean removeBook(int id) throws ServiceException;
    boolean addBook(Book book) throws ServiceException;
    BookTable getAllBooksOnPage(SearchFiltersFromForm searchFiltersFromForm) throws ServiceException;
}
