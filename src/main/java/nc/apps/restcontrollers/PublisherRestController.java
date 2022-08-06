package nc.apps.restcontrollers;

import nc.apps.entities.Publisher;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/publisher/")
public class PublisherRestController {
    PublisherService publisherService;

    @Autowired
    public void setPublisherService(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping("/add/")
    public ResponseEntity addPublisher(@RequestBody Publisher publisher) throws ServiceException {
        boolean result = publisherService.save(publisher);
        if(result){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/getall/")
    public ResponseEntity<List<Publisher>> getAllPublishers() throws ServiceException {
        List<Publisher> publishers = publisherService.getAll();
        if(publishers!=null){
            return new ResponseEntity<>(publishers,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
