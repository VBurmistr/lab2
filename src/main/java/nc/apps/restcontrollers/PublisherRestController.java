package nc.apps.restcontrollers;

import nc.apps.dto.ResponseObject;
import nc.apps.dto.tabledtos.PublisherDTO;
import nc.apps.entities.Language;
import nc.apps.entities.Publisher;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher/")
public class PublisherRestController {
    private final PublisherService publisherService;
    @Autowired
    public PublisherRestController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping("/add/")
    public ResponseEntity addPublisher(@RequestBody PublisherDTO publisher) throws ServiceException {
        publisherService.save(publisher);
        ResponseObject obj = new ResponseObject();
        obj.setSuccess(true);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @GetMapping("/getall/")
    public ResponseEntity<ResponseObject<List<PublisherDTO>>> getAllPublishers() throws ServiceException {
        List<PublisherDTO> publishers = publisherService.getAll();
        ResponseObject<List<PublisherDTO>> obj = new ResponseObject<>();
        obj.setResponseBody(publishers);
        obj.setSuccess(true);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @GetMapping(value ="/remove/{id}")
    public ResponseEntity<ResponseObject> removePublisher(@PathVariable Integer id) throws ServiceException {
        publisherService.remove(id);
        ResponseObject obj = new ResponseObject<>();
        obj.setSuccess(true);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }
}
