package nc.apps.services;

import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.BookDAO;
import nc.apps.entities.Book;
import nc.apps.services.daoServices.BookServiceImpl;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.BookService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

//@ContextConfiguration
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

//    static BookService bookService;
//
//    @BeforeAll
//    static void init() throws DAOException {
//        BookDAO bookDAO = mock(BookDAO.class);
//        bookService = new BookServiceImpl(bookDAO);
//
//        Book book = new Book();
//        book.setId(1);
//
//        Mockito.doReturn(book)
//                .doThrow(new DAOException("Nothing with that id"))
//                .when(bookDAO)
//                .getById(1);
//
//        Mockito.doNothing()
//                .doThrow(new DAOException("Nothing with that id"))
//                .when(bookDAO)
//                .update(any());
//
//        Mockito.doNothing()
//                .doThrow(new DAOException("Nothing with that id"))
//                .when(bookDAO)
//                .remove(1);
//
//        Mockito.doNothing()
//                .doThrow(new DAOException("Nothing with that id"))
//                .when(bookDAO)
//                .save(any());
//
//    }
//
//
//    @Test
//    void getBookById() throws ServiceException {
//
//        Book bookActual= bookService.getBookById(1);
//        Book bookExpected = new Book();
//        bookExpected.setId(1);
//        assertEquals(bookActual, bookExpected);
//
//        bookActual = bookService.getBookById(1);
//        bookExpected = null;
//        assertEquals(bookActual, bookExpected);
//    }
//
//    @Test
//    void updateBook() throws ServiceException {
//        Book book = new Book();
//        book.setId(1);
//
//        boolean result = bookService.updateBook(book,1);
//        assertTrue(result);
//
//        boolean resultEx = bookService.updateBook(book,5);
//        assertFalse(resultEx);
//    }
//
//    @Test
//    void removeBook() throws ServiceException {
//
//        boolean result = bookService.removeBook(1);
//        assertTrue(result);
//
//        boolean resultEx = bookService.removeBook(1);
//        assertFalse(resultEx);
//    }
//
//    @Test
//    void addBook() throws ServiceException {
//
//        boolean result = bookService.addBook(new Book());
//        assertTrue(result);
//
//        boolean resultEx = bookService.addBook(new Book());
//        assertFalse(resultEx);
//    }
}