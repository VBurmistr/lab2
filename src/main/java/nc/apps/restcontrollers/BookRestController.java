package nc.apps.restcontrollers;

import nc.apps.dto.BookIDsDTO;
import nc.apps.dto.ResponseObject;
import nc.apps.dto.tabledtos.BookTable;
import nc.apps.dto.SearchFiltersFromForm;
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
    public ResponseEntity<ResponseObject> removeBook(@PathVariable int id) throws ServiceException {
        bookService.removeBook(id);
        ResponseObject obj = new ResponseObject();
        obj.setSuccess(true);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<ResponseObject> updateBook(@RequestBody BookIDsDTO bookIDsDTO, @PathVariable int id) throws ServiceException {
        bookService.updateBook(bookIDsDTO,id);
        ResponseObject obj = new ResponseObject();
        obj.setSuccess(true);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @GetMapping(value = "/getall/")
    public ResponseEntity<ResponseObject<BookTable>> getBooks(@RequestParam(required = false) String title,
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
        ResponseObject<BookTable> obj = new ResponseObject<>(true,bookTable);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @PostMapping(value = "/add/")
    public ResponseEntity addBook(@RequestBody BookIDsDTO bookIDsDTO) throws ServiceException {
        bookService.addBook(bookIDsDTO);
        ResponseObject obj = new ResponseObject();
        obj.setSuccess(true);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }
}
