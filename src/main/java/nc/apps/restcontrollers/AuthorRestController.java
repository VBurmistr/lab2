package nc.apps.restcontrollers;

import nc.apps.entities.Author;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author/")
public class AuthorRestController {
    private final AuthorService authorService;

    @Autowired
    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(value = "/add/")
    public ResponseEntity addAuthor(@RequestBody Author author) throws ServiceException {
        boolean result = authorService.save(author);
        if(result){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value ="/getall/")
    public ResponseEntity<List<Author>> getAllAuthors() throws ServiceException {
        List<Author> authors = authorService.getAll();
        if(authors!=null){
            return new ResponseEntity<>(authors,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
