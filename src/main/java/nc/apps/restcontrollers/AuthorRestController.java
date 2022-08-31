package nc.apps.restcontrollers;

import nc.apps.dto.ResponseObject;
import nc.apps.dto.tabledtos.AuthorDTO;
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
    public ResponseEntity addAuthor(@RequestBody AuthorDTO author) throws ServiceException {
        authorService.save(author);
        ResponseObject obj = new ResponseObject();
        obj.setSuccess(true);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @GetMapping(value ="/getall/")
    public ResponseEntity<ResponseObject<List<AuthorDTO>>> getAllAuthors() throws ServiceException {
        List<AuthorDTO> authors = authorService.getAll();
        ResponseObject<List<AuthorDTO>> obj = new ResponseObject<>();
        obj.setResponseBody(authors);
        obj.setSuccess(true);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @GetMapping(value ="/remove/{id}")
    public ResponseEntity<ResponseObject> removeAuthor(@PathVariable Integer id) throws ServiceException {
        authorService.remove(id);
        ResponseObject obj = new ResponseObject<>();
        obj.setSuccess(true);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }
}
