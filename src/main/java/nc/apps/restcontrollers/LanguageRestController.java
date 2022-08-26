package nc.apps.restcontrollers;

import nc.apps.dto.ResponseObject;
import nc.apps.dto.tabledtos.LanguageDTO;
import nc.apps.entities.Category;
import nc.apps.entities.Language;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/language/")
public class LanguageRestController {
    private final LanguageService languageService;
    @Autowired
    public LanguageRestController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping("/add/")
    public ResponseEntity addLanguage(@RequestBody LanguageDTO language) throws ServiceException {
        languageService.save(language);
        ResponseObject obj = new ResponseObject();
        obj.setSuccess(true);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @GetMapping("/getall/")
    public ResponseEntity<ResponseObject<List<LanguageDTO>>> getAllLanguages() throws ServiceException {
        List<LanguageDTO> languages = languageService.getAll();
        ResponseObject<List<LanguageDTO>> obj = new ResponseObject<>();
        obj.setResponseBody(languages);
        obj.setSuccess(true);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @GetMapping(value ="/remove/{id}")
    public ResponseEntity<ResponseObject> removeLanguage(@PathVariable Integer id) throws ServiceException {
        languageService.remove(id);
        ResponseObject obj = new ResponseObject<>();
        obj.setSuccess(true);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }
}
