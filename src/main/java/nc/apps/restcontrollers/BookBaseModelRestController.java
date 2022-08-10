package nc.apps.restcontrollers;

import nc.apps.entities.BookBaseModel;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.BookBaseModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookbasemodel/")
public class BookBaseModelRestController {
    private final BookBaseModelService bookBaseModelService;

    @Autowired
    public BookBaseModelRestController(BookBaseModelService bookBaseModelService) {
        this.bookBaseModelService = bookBaseModelService;
    }

    @GetMapping("/getall/")
    public ResponseEntity<List<BookBaseModel>> getAllPrequels() throws ServiceException {
        List<BookBaseModel> bookBaseModels = bookBaseModelService.getAll();
        if(bookBaseModels!=null){
            return new ResponseEntity<>(bookBaseModels, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
