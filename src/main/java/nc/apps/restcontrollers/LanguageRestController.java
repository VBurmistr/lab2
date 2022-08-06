package nc.apps.restcontrollers;

import nc.apps.entities.Language;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/language/")
public class LanguageRestController {
    LanguageService languageService;

    @Autowired
    public void setLanguageService(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping("/add/")
    public ResponseEntity addLanguage(@RequestBody Language language) throws ServiceException {
        boolean result = languageService.save(language);
        if(result){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/getall/")
    public ResponseEntity<List<Language>> getAllLanguages() throws ServiceException {
        List<Language> languages = languageService.getAll();
        if(languages!=null){
            return new ResponseEntity<>(languages,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
