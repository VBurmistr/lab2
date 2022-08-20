package nc.apps.restcontrollers;

import nc.apps.dto.ResponseObject;
import nc.apps.dto.tabledtos.BookBaseModelDTO;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.BookBaseModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<ResponseObject<List<BookBaseModelDTO>>> getAllPrequels() throws ServiceException {
        List<BookBaseModelDTO> bookBaseModels = bookBaseModelService.getAll();
        ResponseObject<List<BookBaseModelDTO>> obj
                = new ResponseObject<>(true,bookBaseModels);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }
}
