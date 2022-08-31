package nc.apps.dao.interfaces;

import nc.apps.dao.exception.DAOException;
import nc.apps.dto.BookDBFilter;
import nc.apps.entities.domain.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getAll(BookDBFilter bookDBFilter) throws DAOException;
    int allBooksSize(BookDBFilter bookDBFilter) throws DAOException;
    Book getById(long id) throws DAOException;
    void save(Book book) throws DAOException;
    void update(Book book) throws DAOException;
    void remove(long id) throws DAOException;

}
