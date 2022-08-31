package nc.apps.services.interfaces;

import nc.apps.dto.BookIDsDTO;
import nc.apps.dto.tabledtos.BookDTO;
import nc.apps.dto.tabledtos.BookTable;
import nc.apps.dto.SearchFiltersFromForm;
import nc.apps.services.exceptions.ServiceException;

public interface BookService {
    BookDTO getBookById(int id) throws ServiceException;
    boolean updateBook(BookIDsDTO book, int id) throws ServiceException;
    boolean removeBook(int id) throws ServiceException;
    boolean addBook(BookIDsDTO book) throws ServiceException;
    BookTable getAllBooksOnPage(SearchFiltersFromForm searchFiltersFromForm) throws ServiceException;
}
