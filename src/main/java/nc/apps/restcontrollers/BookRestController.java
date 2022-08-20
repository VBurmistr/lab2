package nc.apps.restcontrollers;

import nc.apps.dto.BookIDsDTO;
import nc.apps.dto.BookTable;
import nc.apps.dto.SearchFiltersFromForm;
import nc.apps.entities.Book;
import nc.apps.mappers.BookIdsDTOToBookDomain;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book/")
public class BookRestController {
    private final BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/remove/{id}")
    public ResponseEntity removeBook(@PathVariable int id) throws ServiceException {
        boolean result = bookService.removeBook(id);
        if (result) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity updateBook(@RequestBody BookIDsDTO bookIDsDTO, @PathVariable int id) throws ServiceException {
        Book newBook = BookIdsDTOToBookDomain.map(bookIDsDTO);
        boolean result = bookService.updateBook(newBook,id);
        if (result) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/getall/")
    public ResponseEntity<BookTable> getBooks(@RequestParam(required = false) String title,
                                              @RequestParam(required = false) String authorName,
                                              @RequestParam(required = false) String category,
                                              @RequestParam(required = false) String language,
                                              @RequestParam(required = false) String publisher,
                                              @RequestParam(required = false) String orderBy,
                                              @RequestParam(required = false) String ordering,
                                              @RequestParam(required = false) Integer page
                                              ) throws ServiceException {
        SearchFiltersFromForm searchFiltersFromForm =
                new SearchFiltersFromForm(page,title,authorName,category,language,publisher,orderBy,ordering);

        BookTable bookTable = bookService.getAllBooksOnPage(searchFiltersFromForm);
        if (bookTable != null) {
            return new ResponseEntity<>(bookTable, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/add/")
    public ResponseEntity addBook(@RequestBody BookIDsDTO bookIDsDTO) throws ServiceException {
        Book newBook = BookIdsDTOToBookDomain.map(bookIDsDTO);
        boolean result = bookService.addBook(newBook);
        if (result) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
