package nc.apps.restcontrollers;

import nc.apps.dto.AuthorAndTitle;
import nc.apps.dto.BookTable;
import nc.apps.dto.SearchFiltersFromForm;
import nc.apps.entities.Book;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book/")
public class BookRestController {
    BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/remove/")
    public ResponseEntity removeBook(@RequestParam int id) throws ServiceException {
        boolean result = bookService.removeBook(id);
        if(result){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity updateBook(@RequestBody Book book, @PathVariable long id) throws ServiceException {
        boolean result = bookService.updateBook(book,id);
        if(result){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/getall/")
    public ResponseEntity<BookTable> getBooks(@RequestBody SearchFiltersFromForm searchFiltersFromForm) throws ServiceException {
        BookTable bookTable = bookService.getAllBooksOnPage(searchFiltersFromForm);
        if(bookTable!=null){
            return new ResponseEntity<>(bookTable,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/add/")
    public ResponseEntity addBook(@RequestBody Book book) throws ServiceException {
        boolean result = bookService.addBook(book);
        if(result){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/addsmart/")
    public ResponseEntity addBookSmart(@RequestBody AuthorAndTitle authorAndTitle) throws ServiceException {


        return null;
    }
}
